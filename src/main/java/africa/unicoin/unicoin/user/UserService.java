package africa.unicoin.unicoin.user;

import java.util.Optional;

public interface UserService {
    public String createAccount(User user);
    Optional<User> findUserByEmailAddressIgnoreCase(String emailAddress);
    void enableUser(String email);
}