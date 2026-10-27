package school21.authservice.Mapper;

import org.springframework.stereotype.Component;
import school21.authservice.Dto.UserDto;
import school21.authservice.Persistence.Entity.UserEntity;
@Component
public class Mapper {

    public UserEntity dtoToEntity(UserDto userDto, String hash) {
        if (userDto == null) {
            throw new IllegalArgumentException("Error.Object is null");
        }
        return new UserEntity(userDto.getName(), userDto.getSurname(), userDto.getPhone(), userDto.getLogin(), hash);
    }
}
