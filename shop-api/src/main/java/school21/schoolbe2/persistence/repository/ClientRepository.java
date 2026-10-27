package school21.schoolbe2.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import school21.schoolbe2.persistence.entity.ClientEntity;

import java.util.List;
import java.util.UUID;

public interface ClientRepository extends CrudRepository<ClientEntity, UUID> {

    public List<ClientEntity> findByClientNameAndClientSurname(String clientName, String clientSurname);
    @Query(value = "Select * from client order by id limit :limit offset :offset",nativeQuery = true)
    List<ClientEntity> findAllClientLimitAndOffset(@Param("limit") Integer limit,@Param("offset") Integer offset);
}
