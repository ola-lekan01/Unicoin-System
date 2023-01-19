package africa.unicoin.unicoin.registration;

import africa.unicoin.unicoin.registration.dtos.RegistrationRequest;
import africa.unicoin.unicoin.registration.mockutils.MockUtils;
import africa.unicoin.unicoin.user.User;
import africa.unicoin.unicoin.user.UserServiceImpl;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegistrationServiceImplTest {

    private final UserServiceImpl userServiceMock = spy(MockUtils.userServiceMock());
    private final RegistrationService registrationService = new RegistrationServiceImpl(
            userServiceMock,
            MockUtils.emailSenderMock,
            MockUtils.confirmationTokenServiceMock()
    );

    @Test
    void register() throws MessagingException {
        RegistrationRequest registrationRequest = new RegistrationRequest(
                "James",
                "Lakes",
                "lakes_lakes123@gmail.com",
                "12345"
        );

        registrationService.register(registrationRequest);

        doReturn("f1884964-a3d0-4a9a-ba95-d6c35b4f58db").when(userServiceMock).createAccount(any(User.class));

        assertEquals(registrationService.register(registrationRequest), "f1884964-a3d0-4a9a-ba95-d6c35b4f58db");
    }
}