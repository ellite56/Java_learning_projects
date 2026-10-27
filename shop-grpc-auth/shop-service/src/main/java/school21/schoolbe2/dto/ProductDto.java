package school21.schoolbe2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Schema(description = "Данные товара")
public class ProductDto {
    @Schema(description = "UUID товара", example = "77f15651-1234-4567-8901-123456789012")
    private UUID productId;
    @NotBlank(message = "Product name is empty")
    @Schema(description = "Название товара", example = "Холодильник Samsung")
    private String productName;
    @NotBlank(message = "Category is empty")
    @Schema(description = "Категория товара", example = "Холодильники")
    private String category;
    @NotNull(message = "Price is null")
    @PositiveOrZero(message = "Price cannot be negative")
    @Schema(description = "Цена товара", example = "79990.00")
    private BigDecimal price;
    @PositiveOrZero(message = "Available stock cannot be negative")
    @Schema(description = "Количество товара на складе", example = "10")
    private int availableStock;
    @NotNull(message = "Last update date is null")
    @PastOrPresent(message = "Last update date cannot be in the future")
    @Schema(description = "Дата последнего обновления товара", example = "2026-09-24")
    private LocalDate lastUpdateDate;
    @NotNull(message = "Supplier id is null")
    @Schema(description = "UUID поставщика", example = "2be5825c-7cc1-4afa-9947-e883491a44fc")
    private UUID supplierId;
    @Schema(description = "UUID изображения товара", example = "e9dbca33-2a9c-4a73-b1d7-f211c5363d06")
    private UUID imageId;

    public ProductDto() {
    }

    public ProductDto(UUID productId, String productName, String category, BigDecimal price,
                      int availableStock, LocalDate lastUpdateDate, UUID supplierId, UUID imageId) {
        this.availableStock = availableStock;
        this.productId = productId;
        this.productName = productName;
        this.imageId = imageId;
        this.category = category;
        this.lastUpdateDate = lastUpdateDate;
        this.price = price;
        this.supplierId = supplierId;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public UUID getImageId() {
        return imageId;
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

    public UUID getSupplierId() {
        return supplierId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public void setSupplierId(UUID supplierId) {
        this.supplierId = supplierId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void setImageId(UUID imageId) {
        this.imageId = imageId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
