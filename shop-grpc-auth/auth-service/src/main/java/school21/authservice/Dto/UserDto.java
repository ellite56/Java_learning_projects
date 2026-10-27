package school21.authservice.Dto;

import jakarta.validation.constraints.Email;

public class UserDto {
    private String name;
    private String surname;
    private String phone;
    @Email(message = "Некорректный адрес почты")
    private String login;
    private String password;

    public UserDto() {
    }

    public UserDto(String name, String surname, String phone, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}


