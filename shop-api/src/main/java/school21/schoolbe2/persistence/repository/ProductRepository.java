package school21.schoolbe2.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import school21.schoolbe2.persistence.entity.ProductEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends CrudRepository<ProductEntity, UUID> {

    public List<ProductEntity> findByAvailableStockGreaterThan(int Stock);
    public Optional<ProductEntity> findByImageEntityImageId(UUID id);
    boolean existsBySupplierSupplierId(UUID supplierId);
}
