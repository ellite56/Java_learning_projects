package school21.schoolbe2.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import school21.schoolbe2.persistence.entity.SupplierEntity;

import java.util.UUID;

public interface SupplierRepository extends CrudRepository<SupplierEntity, UUID> {
}
