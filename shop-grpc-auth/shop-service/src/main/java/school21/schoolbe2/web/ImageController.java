package school21.schoolbe2.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school21.schoolbe2.auth.Authorize;
import school21.schoolbe2.dto.ImageDto;
import school21.schoolbe2.service.ShopService;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.UUID;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
@Tag(name = "Images", description = "Работа с изображениями товаров")
@Authorize
@RestController
@RequestMapping("/api/v1/images")
public class ImageController {
    private final ShopService service;

    public ImageController(ShopService service) {
        this.service = service;
    }

    @Operation(summary = "Добавить изображение", description = "Добавляет изображение к товару по его UUID")
    @ApiResponse(responseCode = "200", description = "Изображение успешно добавлено")
    @ApiResponse(responseCode = "400", description = "Некорректные данные изображения")
    @ApiResponse(responseCode = "404", description = "Товар не найден")
    @PostMapping("/{productId}")
    public ImageDto addImage(@RequestBody(required = false) byte[] arr, @Parameter(description = "UUID товара") @PathVariable UUID productId) {
        return service.addImage(arr, productId);
    }

    @Operation(summary = "Изменить изображение", description = "Заменяет изображение по его UUID")
    @ApiResponse(responseCode = "200", description = "Изображение успешно изменено")
    @ApiResponse(responseCode = "400", description = "Некорректные данные изображения")
    @ApiResponse(responseCode = "404", description = "Изображение не найдено")
    @PutMapping("/{imageId}")
    public ImageDto updateImage(@RequestBody(required = false) byte[] arr, @Parameter(description = "UUID изображения") @PathVariable UUID imageId) {
        return service.updateImage(imageId, arr);
    }

    @Operation(summary = "Удалить изображение", description = "Удаляет изображение по его UUID")
    @ApiResponse(responseCode = "200", description = "Изображение успешно удалено")
    @ApiResponse(responseCode = "404", description = "Изображение не найдено")
    @DeleteMapping("/{imageId}")
    public void deleteImageById(@Parameter(description = "UUID изображения") @PathVariable UUID imageId) {
        service.deleteImageById(imageId);
    }

    @Operation(summary = "Получить изображение товара", description = "Возвращает изображение товара по UUID товара в виде массива байт")
    @ApiResponse(responseCode = "200", description = "Изображение успешно получено")
    @ApiResponse(responseCode = "404", description = "Товар или изображение не найдены")
    @GetMapping(value = "/product/{productId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE )
    public ResponseEntity<byte[]> getImageByProductId(@Parameter(description = "UUID товара") @PathVariable UUID productId) {
        byte[] image = service.getImageProduct(productId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).header(HttpHeaders.CONTENT_DISPOSITION, "attachment").body(image);
    }

    @Operation(summary = "Получить изображение по id", description = "Возвращает изображение по его UUID в виде массива байт")
    @ApiResponse(responseCode = "200", description = "Изображение успешно получено")
    @ApiResponse(responseCode = "404", description = "Изображение не найдено")
    @GetMapping(value = "/{imageId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> arr(@Parameter(description = "UUID изображения") @PathVariable UUID imageId) {
        byte[] image = service.getImageById(imageId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).header(HttpHeaders.CONTENT_DISPOSITION, "attachment").body(image);
    }

}
