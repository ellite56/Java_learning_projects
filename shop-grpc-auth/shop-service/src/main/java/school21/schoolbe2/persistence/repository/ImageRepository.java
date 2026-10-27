package school21.schoolbe2.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import school21.schoolbe2.persistence.entity.ImageEntity;

import java.util.UUID;

public interface ImageRepository extends CrudRepository<ImageEntity, UUID> {
}
