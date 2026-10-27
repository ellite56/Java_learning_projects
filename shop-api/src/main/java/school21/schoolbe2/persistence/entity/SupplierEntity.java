package school21.schoolbe2.persistence.entity;

import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Table(name = "supplier")
public class SupplierEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID supplierId;
    private String name;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;
    @Column(name = "phone_number")
    private String phoneNumber;

    public SupplierEntity(){

    }
    public SupplierEntity(String name,AddressEntity address,String phoneNumber){
        this.address=address;
        this.name=name;
        this.phoneNumber=phoneNumber;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UUID getSupplierId() {
        return supplierId;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }
}
