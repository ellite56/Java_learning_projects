package school21.schoolbe2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.UUID;
@Schema(description = "Данные клиента")
public class ClientDto {
    @Schema(description = "UUID клиента", example = "8fd32718-8f7c-4a9f-9b8a-116ce9e0f7c2")
    private UUID id;
    @NotBlank(message = "Client name is empty")
    @Schema(description = "Имя клиента", example = "Anton")
    private String clientName;
    @NotBlank(message = "Client surname is empty")
    @Schema(description = "Фамилия клиента", example = "Shelegov")
    private String clientSurname;
    @NotNull(message = "Birthday is null")
    @Past(message = "Birthday must be in the past")
    @Schema(description = "Дата рождения клиента", example = "2003-05-17")
    private LocalDate birthday;
    @NotBlank(message = "Gender is empty")
    @Schema(description = "Пол клиента", example = "Male")
    private String gender;
    @NotNull(message = "Registration date is null")
    @PastOrPresent(message = "Registration date cannot be in the future")
    @Schema(description = "Дата регистрации клиента", example = "2026-09-24")
    private LocalDate registrationDate;
    @Valid
    @NotNull(message = "Address is null")
    @Schema(description = "Адрес клиента")
    private AddressDto address;

    public ClientDto(){}

    public ClientDto(UUID Id,String ClientName,String ClientSurname,LocalDate Birthday,String Gende
            ,LocalDate registrationDate,AddressDto Address){
        this.address=Address;
        this.clientName=ClientName;
        this.id=Id;
        this.clientSurname=ClientSurname;
        this.gender=Gende;
        this.birthday=Birthday;
        this.registrationDate=registrationDate;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String getGender() {
        return gender;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

}
