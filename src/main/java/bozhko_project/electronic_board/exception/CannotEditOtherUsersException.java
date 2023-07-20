package bozhko_project.electronic_board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.bind.ValidationException;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CannotEditOtherUsersException extends ValidationException {
    private static final String DEFAULT_ERROR_MESSAGE = "Нельзя редактировать данные других пользователей";

    public CannotEditOtherUsersException() {
        super(DEFAULT_ERROR_MESSAGE);
    }


}
