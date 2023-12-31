package bozhko_project.electronic_board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class UserNotFoundException extends RuntimeException {
    private static final String INTERNAL_SERVER_ERROR = "Пользователь не найден";

    public UserNotFoundException() {
        super(INTERNAL_SERVER_ERROR);
    }
}
