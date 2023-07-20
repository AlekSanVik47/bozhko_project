package bozhko_project.electronic_board.dto.dto_user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Schema(description = "Авторизация пользователя")
public class UserAuthDTO {

	@Schema(description = "Логин пользователя")

	private String login;

	@Schema(description = "пароль")
	private String password;
}
