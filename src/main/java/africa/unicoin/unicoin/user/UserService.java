package africa.unicoin.unicoin.user;

import africa.unicoin.unicoin.user.dtos.LoginRequest;
import africa.unicoin.unicoin.user.dtos.PasswordResetRequest;

import java.util.Optional;

public interface UserService {
    public String createAccount(User user);
    Optional<User> findUserByEmailAddressIgnoreCase(String emailAddress);
    void enableUser(String email);
    User saveUser(User foundUser);
    String login(LoginRequest loginRequest);
    String resendToken(String email);
    String changePassword(PasswordResetRequest passwordReset);
}