package school21.schoolbe2.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "client_name")
    private String clientName;
    @Column(name = "client_surname")
    private String clientSurname;
    private LocalDate  birthday;
    private String gender;
    @Column(name = "registration_date")
    private LocalDate  registrqtionDate;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    public ClientEntity(){

    }

    public ClientEntity(String ClientName,String ClientSurname,LocalDate  Birthday,String Gende
    ,LocalDate  RegistrqtionDate,AddressEntity Address){
        this.address=Address;
        this.clientName=ClientName;
        this.clientSurname=ClientSurname;
        this.gender=Gende;
        this.birthday=Birthday;
        this.registrqtionDate=RegistrqtionDate;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate  getBirthday() {
        return birthday;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public LocalDate  getRegistrqtionDate() {
        return registrqtionDate;
    }

    public String getGender() {
        return gender;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }
}
