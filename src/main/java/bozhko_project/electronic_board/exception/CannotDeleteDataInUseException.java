package bozhko_project.electronic_board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.rmi.ServerError;
import java.sql.SQLException;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class CannotDeleteDataInUseException extends SQLException {
	private static final String DATA_IS_USED ="Нельзя удалить используемые данные";
	public CannotDeleteDataInUseException(){
		super(DATA_IS_USED);
	}
}
