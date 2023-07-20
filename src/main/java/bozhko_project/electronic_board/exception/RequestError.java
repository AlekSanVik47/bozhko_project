package bozhko_project.electronic_board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class RequestError extends Exception{
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception)
    {
        return handleBadRequestError("Parse error!");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception)
    {
        return handleBadRequestError("Required fields are not set or not valid (such as phone number or telegram account)!");
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<?> handleBindException(BindException exception)
    {
        return handleBadRequestError("Request validation error!");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException exception)
    {
        return handleBadRequestError("Entity with given request parameters not found!");
    }

    private final ResponseEntity<?> handleBadRequestError(String errorMessage)
    {
        Map<String, Object> errorAttributes = new LinkedHashMap();

        errorAttributes.put("timestamp", LocalDateTime.now());
        errorAttributes.put("status", HttpStatus.BAD_REQUEST);
        errorAttributes.put("error", errorMessage);

        return new ResponseEntity<>(errorAttributes, HttpStatus.BAD_REQUEST);
    }
}
