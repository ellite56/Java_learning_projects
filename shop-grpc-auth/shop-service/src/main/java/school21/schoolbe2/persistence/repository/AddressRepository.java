package school21.schoolbe2.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import school21.schoolbe2.persistence.entity.AddressEntity;

import java.util.UUID;

public interface AddressRepository extends CrudRepository<AddressEntity, UUID> {
}
