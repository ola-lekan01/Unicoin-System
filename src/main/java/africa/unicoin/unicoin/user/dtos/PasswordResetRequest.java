package africa.unicoin.unicoin.user.dtos;

import lombok.Data;

@Data
public class PasswordResetRequest {
    private String email;
    private String OldPassword;
    private String NewPassword;
}
