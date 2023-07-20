package bozhko_project.electronic_board.dto.dto_user;

import bozhko_project.electronic_board.entities.users.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String login;
    private Role role;

}

