package school21.schoolbe2.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import school21.schoolbe2.auth.Authorize;
import school21.schoolbe2.dto.ProductDto;
import school21.schoolbe2.service.ShopService;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;
import java.util.UUID;
@Tag(name = "Products",description = "Работа с товарами")
@Authorize
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ShopService service;

    public ProductController(ShopService service){
        this.service=service;
    }

    @Operation(summary = "Добавить товар", description = "Добавляет новый товар и возвращает добавленный товар")
    @ApiResponse(responseCode = "200", description = "Товар успешно добавлен")
    @ApiResponse(responseCode = "400", description = "Некорректные данные товара")
    @ApiResponse(responseCode = "404", description = "Поставщик или изображение не найдены")
    @PostMapping
    public ProductDto addNewProduct(@Valid @RequestBody ProductDto productDto) {
        return service.AddNewProdact(productDto);
    }

    @Operation(summary = "Уменьшить количество товара", description = "Уменьшает количество доступного товара на переданное значение")
    @ApiResponse(responseCode = "200", description = "Количество товара успешно уменьшено")
    @ApiResponse(responseCode = "400", description = "Некорректное значение уменьшения")
    @ApiResponse(responseCode = "404", description = "Товар не найден")
    @PatchMapping("/{id}")
    public ProductDto reduceProduct(@Parameter(description = "UUID товара") @PathVariable UUID id, @Parameter(description = "Количество товара для уменьшения", example = "2") @RequestParam int count) {
        return service.ReduceProduct(id, count);
    }

    @Operation(summary = "Получить товар по id", description = "Возвращает товар по его UUID")
    @ApiResponse(responseCode = "200", description = "Товар найден")
    @ApiResponse(responseCode = "404", description = "Товар не найден")
    @GetMapping("/{id}")
    public ProductDto getProductById(@Parameter(description = "UUID товара") @PathVariable UUID id) {
        return service.GetProductId(id);
    }

    @Operation(summary = "Получить все доступные товары", description = "Возвращает список товаров, имеющихся в наличии")
    @ApiResponse(responseCode = "200", description = "Список товаров успешно получен")
    @GetMapping
    public List<ProductDto> getAllProduct() {
        return service.GetAllAvailableProduct();
    }

    @Operation(summary = "Удалить товар по UUID", description = "Удаляет товар по его идентификатору")
    @ApiResponse(responseCode = "200", description = "Товар успешно удалён")
    @ApiResponse(responseCode = "404", description = "Товар не найден")
    @DeleteMapping("/{id}")
    public void deleteProductById(@Parameter(description = "UUID товара") @PathVariable UUID id) {
        service.DeleteProductById(id);
    }
}
