package bozhko_project.electronic_board.service;

import bozhko_project.electronic_board.dto.dto_user.UserCreationDTO;
import bozhko_project.electronic_board.entities.users.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface UserService extends UserDetailsService {
	User findByUserLogin (String login);

	@Transactional
	boolean saveUser(UserCreationDTO creationDTO);
}
