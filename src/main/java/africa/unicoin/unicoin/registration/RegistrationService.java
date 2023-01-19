package africa.unicoin.unicoin.registration;

import africa.unicoin.unicoin.registration.dtos.ConfirmationTokenRequest;
import africa.unicoin.unicoin.registration.dtos.RegistrationRequest;
import africa.unicoin.unicoin.user.User;
import jakarta.mail.MessagingException;

public interface RegistrationService {
    String register (RegistrationRequest request) throws MessagingException;
    public String confirmToken(ConfirmationTokenRequest request);
    void enableUser(ConfirmationTokenRequest confirmationTokenRequest);
}
