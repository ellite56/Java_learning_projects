package school21.schoolbe2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Schema(description = "Данные адреса")
public class AddressDto {
    @Schema(description = "UUID адреса", example = "c1c9a7c4-8d3e-4f22-9a6b-2fda8c5a7e11")
    private UUID addressId;
    @NotBlank(message = "Country is empty")
    @Schema(description = "Страна", example = "Russia")
    private String country;
    @NotBlank(message = "City is empty")
    @Schema(description = "Город", example = "Novosibirsk")
    private String city;
    @NotBlank(message = "Street is empty")
    @Schema(description = "Улица", example = "Lenina 10")
    private String street;

    public AddressDto(){}

    public AddressDto(UUID addressId,String country,String city,String street){
        this.addressId=addressId;
        this.city=city;
        this.country=country;
        this.street=street;
    }

    public UUID getAddressId() {
        return addressId;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }
}
