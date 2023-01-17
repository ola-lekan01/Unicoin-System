package africa.unicoin.unicoin.registration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RegistrationRequest {

    private @NotBlank(message = "This field is required") String firstName;
    private @NotBlank(message = "This field is required") String lastName;
    private @Email(message = "This Field requires a valid Email") String emailAddress;
    private @NotBlank(message = "This field is required") String password;
}