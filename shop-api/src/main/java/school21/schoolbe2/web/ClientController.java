package school21.schoolbe2.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import school21.schoolbe2.dto.AddressDto;
import school21.schoolbe2.dto.ClientDto;
import school21.schoolbe2.service.SchopService;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import java.util.UUID;
@Tag(name = "Clients", description = "Работа с клиентами")
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final SchopService service;

    public ClientController(SchopService service){
        this.service=service;
    }

    @Operation(summary = "Добавить клиента", description = "Добавляет нового клиента и возвращает добавленного клиента")
    @ApiResponse(responseCode = "200", description = "Клиент успешно добавлен")
    @ApiResponse(responseCode = "400", description = "Некорректные данные клиента")
    @PostMapping
    public ClientDto addNewClient(@Valid @RequestBody ClientDto clientDto){
        return service.AddClient(clientDto);
    }

    @Operation(summary = "Удалить клиента", description = "Удаляет клиента по его UUID")
    @ApiResponse(responseCode = "200", description = "Клиент успешно удалён")
    @ApiResponse(responseCode = "404", description = "Клиент не найден")
    @DeleteMapping("/{id}")
    public void deleteClient(@Parameter(description = "UUID клиента") @PathVariable UUID id){
        service.RemoveClientId(id);
    }

    @Operation(summary = "Получить клиентов по имени и фамилии", description = "Возвращает список клиентов с указанными именем и фамилией")
    @ApiResponse(responseCode = "200", description = "Список клиентов успешно получен")
    @ApiResponse(responseCode = "400", description = "Некорректные имя или фамилия")
    @GetMapping("/search")
    public List<ClientDto> getClientNameAndSurname(@Parameter(description = "Имя клиента", example = "Anton") @RequestParam String name, @Parameter(description = "Фамилия клиента", example = "Shelegov") @RequestParam String surname){
        return service.GetClientNameSurname(name,surname);
    }

    @Operation(summary = "Получить всех клиентов", description = "Возвращает список клиентов с возможностью использования limit и offset")
    @ApiResponse(responseCode = "200", description = "Список клиентов успешно получен")
    @ApiResponse(responseCode = "400", description = "Некорректные параметры пагинации")
    @GetMapping
    public List<ClientDto> getAllClient(@Parameter(description = "Максимальное количество клиентов", example = "10") @RequestParam(required = false) Integer limit, @Parameter(description = "Количество пропускаемых клиентов", example = "0") @RequestParam(required = false) Integer offset){
        return service.getAllClient(limit,offset);
    }

    @Operation(summary = "Изменить адрес клиента", description = "Изменяет адрес клиента по его UUID")
    @ApiResponse(responseCode = "200", description = "Адрес клиента успешно изменён")
    @ApiResponse(responseCode = "400", description = "Некорректные данные адреса")
    @ApiResponse(responseCode = "404", description = "Клиент не найден")
    @PutMapping("/{id}/address")
    public ClientDto updateAddress(@Valid @RequestBody AddressDto addressDto, @Parameter(description = "UUID клиента") @PathVariable UUID id){
        return service.updateAddress(id,addressDto);
    }
}
