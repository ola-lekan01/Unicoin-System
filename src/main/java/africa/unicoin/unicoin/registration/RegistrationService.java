package africa.unicoin.unicoin.registration;

import jakarta.mail.MessagingException;

public interface RegistrationService {
    String register (RegistrationRequest request) throws MessagingException;
    public String confirmToken(String confirmationToken);
}
