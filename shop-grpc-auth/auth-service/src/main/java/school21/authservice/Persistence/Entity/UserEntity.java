package school21.authservice.Persistence.Entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String surname;
    private String phone;
    private String login;
    private String passwordhash;

    public UserEntity() {
    }

    public UserEntity(String name, String surname, String phone, String login, String passwordhash) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.login = login;
        this.passwordhash = passwordhash;
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

    public UUID getid() {
        return id;
    }

    public void setid(UUID id) {
        this.id = id;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public String getPhone() {
        return phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
