package school21.authservice.Dto;

import java.util.UUID;

public class TokenValidationDto {

    private boolean valid;
    private UUID id;

    public TokenValidationDto(boolean valid,UUID id){
        this.id=id;
        this.valid=valid;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
