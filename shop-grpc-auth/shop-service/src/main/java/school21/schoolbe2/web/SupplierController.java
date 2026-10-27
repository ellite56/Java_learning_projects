package school21.schoolbe2.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import school21.schoolbe2.auth.Authorize;
import school21.schoolbe2.dto.AddressDto;
import school21.schoolbe2.dto.SupplierDto;
import school21.schoolbe2.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import java.util.UUID;

@Tag(name = "Suppliers", description = "Работа с поставщиками")
@Authorize
@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {
    private final ShopService service;

    public SupplierController(ShopService service) {
        this.service = service;
    }


    @Operation(summary = "Добавить поставщика", description = "Добавляет нового поставщика и возвращает добавленного поставщика")
    @ApiResponse(responseCode = "200", description = "Поставщик успешно добавлен")
    @ApiResponse(responseCode = "400", description = "Некорректные данные поставщика")
    @PostMapping
    public SupplierDto addSupplier(@Valid @RequestBody SupplierDto supplierDto) {
        return service.addSupplier(supplierDto);
    }

    @Operation(summary = "Изменить адрес поставщика", description = "Изменяет адрес поставщика по его UUID")
    @ApiResponse(responseCode = "200", description = "Адрес поставщика успешно изменён")
    @ApiResponse(responseCode = "400", description = "Некорректные данные адреса")
    @ApiResponse(responseCode = "404", description = "Поставщик не найден")
    @PutMapping("/{id}/address")
    public SupplierDto updateAddress(@Valid @RequestBody AddressDto addressDto, @Parameter(description = "UUID поставщика") @PathVariable UUID id) {
        return service.updateAddress(addressDto, id);
    }

    @Operation(summary = "Удалить поставщика", description = "Удаляет поставщика по его UUID")
    @ApiResponse(responseCode = "200", description = "Поставщик успешно удалён")
    @ApiResponse(responseCode = "400", description = "Поставщик имеет товары и не может быть удалён")
    @ApiResponse(responseCode = "404", description = "Поставщик не найден")
    @DeleteMapping("/{supplierId}")
    public void deleteSupplier(@Parameter(description = "UUID поставщика") @PathVariable UUID supplierId) {
        service.deleteSupplierById(supplierId);
    }

    @Operation(summary = "Получить всех поставщиков", description = "Возвращает список всех поставщиков")
    @ApiResponse(responseCode = "200", description = "Список поставщиков успешно получен")
    @GetMapping
    public List<SupplierDto> getAllSupplier() {
        return service.getAllSupplier();
    }

    @Operation(summary = "Получить поставщика по id", description = "Возвращает поставщика по его UUID")
    @ApiResponse(responseCode = "200", description = "Поставщик найден")
    @ApiResponse(responseCode = "404", description = "Поставщик не найден")
    @GetMapping("/{supplierId}")
    public SupplierDto getSupplierById(@Parameter(description = "UUID поставщика") @PathVariable UUID supplierId) {
        return service.getSupplierById(supplierId);
    }

}
