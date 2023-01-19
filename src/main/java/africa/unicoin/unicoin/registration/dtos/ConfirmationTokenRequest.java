package africa.unicoin.unicoin.registration.dtos;

import lombok.Data;

@Data
public class ConfirmationTokenRequest {

    private String token;
    private String email;

}
