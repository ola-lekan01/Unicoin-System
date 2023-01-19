package africa.unicoin.unicoin.user;

import africa.unicoin.unicoin.user.dtos.LoginRequest;
import africa.unicoin.unicoin.user.dtos.PasswordResetRequest;
import africa.unicoin.unicoin.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("api/v1/login")

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest,
                                   HttpServletRequest httpServletRequest){

        var loggedUser = userService.login(loginRequest);

        ApiResponse response = ApiResponse.builder().
                status(HttpStatus.CREATED.value())
                .isSuccessful(true)
                .timestamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .data(loggedUser).
                build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("reset/password")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest passwordReset,
                                   HttpServletRequest httpServletRequest){

        var loggedUser = userService.changePassword(passwordReset);

        ApiResponse response = ApiResponse.builder().
                status(HttpStatus.CREATED.value())
                .isSuccessful(true)
                .timestamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .data(loggedUser).
                build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
