package school21.schoolbe2.persistence.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "prodact")
public class ProductEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;
    @Column(name = "name")
    private String productName;
    private String category;
    private BigDecimal price;
    @Column(name = "available_stock")
    private int availableStock;
    @Column(name = "last_update_date")
    private LocalDate lastUpdateDate;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;
    @OneToOne
    @JoinColumn(name = "image_id")
    private ImageEntity imageEntity;

    public ProductEntity() {

    }

    public ProductEntity(String productName, String category, BigDecimal price,
                         int availableStock, LocalDate lastUpdateDate, SupplierEntity supplier, ImageEntity imageEntity) {
        this.availableStock = availableStock;
        this.productName = productName;
        this.imageEntity = imageEntity;
        this.category = category;
        this.lastUpdateDate = lastUpdateDate;
        this.price = price;
        this.supplier = supplier;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public ImageEntity getImageEntity() {
        return imageEntity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public String getCategory() {
        return category;
    }

    public String getProductName() {
        return productName;
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public void setImageEntity(ImageEntity imageEntity) {
        this.imageEntity = imageEntity;
    }
}
