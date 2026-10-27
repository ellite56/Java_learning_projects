package school21.schoolbe2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Schema(description = "Данные поставщика")
public class SupplierDto {
    @Schema(description = "UUID поставщика", example = "2be5825c-7cc1-4afa-9947-e883491a44fc")
    private UUID supplierId;
    @NotBlank(message = "Supplier name is empty")
    @Schema(description = "Название поставщика", example = "TechnoSupply")
    private String name;
    @Valid
    @NotNull(message = "Address is null")
    @Schema(description = "Адрес поставщика")
    private AddressDto address;
    @NotBlank(message = "Phone number is empty")
    @Schema(description = "Номер телефона поставщика", example = "+79991234567")
    private String phoneNumber;

    public SupplierDto(){}

    public SupplierDto(UUID supplierId,String name,AddressDto address,String phoneNumber){
        this.address=address;
        this.name=name;
        this.supplierId=supplierId;
        this.phoneNumber=phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UUID getSupplierId() {
        return supplierId;
    }

    public String getName() {
        return name;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public void setSupplierId(UUID supplierId) {
        this.supplierId = supplierId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
