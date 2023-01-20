package africa.unicoin.unicoin.registration.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConfirmationTokenRequest {

    private String token;
    private String email;

}
