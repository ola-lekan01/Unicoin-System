package africa.unicoin.unicoin.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.ZonedDateTime;


@Builder
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
    private ZonedDateTime timestamp;
    private boolean isSuccessful;
    private Object data;
    private int status;
    private String path;
}
