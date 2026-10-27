package school21.authservice.Persistence.Repository;

import org.springframework.data.repository.CrudRepository;
import school21.authservice.Persistence.Entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserReposotory extends CrudRepository<UserEntity, UUID> {
    public boolean existsByLogin(String login);
    public Optional<UserEntity> findByLogin(String login);
}
