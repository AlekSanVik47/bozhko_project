package bozhko_project.electronic_board.service;

import bozhko_project.electronic_board.dto.dto_user.UserAuthDTO;
import bozhko_project.electronic_board.dto.dto_user.UserCreationDTO;
import bozhko_project.electronic_board.dto.dto_user.UserDTO;
import bozhko_project.electronic_board.dto.dto_user.UserUpdateDTO;
import bozhko_project.electronic_board.entities.users.Role;
import bozhko_project.electronic_board.entities.users.User;
import bozhko_project.electronic_board.exception.DataNotInDBException;
import bozhko_project.electronic_board.exception.UserNotFoundException;
import bozhko_project.electronic_board.mapper.UserMapper;
import bozhko_project.electronic_board.repository.ads_repository.AnnouncementRepository;
import bozhko_project.electronic_board.repository.users_repository.RoleRepository;
import bozhko_project.electronic_board.repository.users_repository.StateRepository;
import bozhko_project.electronic_board.repository.users_repository.StatusRepository;
import bozhko_project.electronic_board.repository.users_repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Component
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private final UserMapper userMapper;
	@Autowired
	private final UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private final RoleRepository roleRepository;
	@Autowired
	private final StateRepository stateRepository;
	@Autowired
	private final StatusRepository statusRepository;
	@Autowired
	private final AnnouncementRepository announcementRepository;



	@Transactional
	@Override
	public User findByUserLogin(String login) {
		return userRepository.findUserByLogin(login);
	}

	@Transactional
	@Override
	public boolean saveUser(UserCreationDTO creationDTO) {
		User user = userMapper.userCreationToUser(creationDTO);
		if (findByUserLogin(creationDTO.getLogin()) != null) {
			return false;
		}
		List<Role> roles = new ArrayList();
		roles.add(roleRepository.findByRole("USER"));
		user.setPassword(passwordEncoder.encode(creationDTO.getPassword()));
		user.setState(stateRepository.findByState("CONFIRMED"));
		user.setRoles(roles);
		user.setStatuses(statusRepository.findByStatus("NEW"));
		userRepository.save(user);
		return true;
	}


	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<User> userOptional = Optional.ofNullable(userRepository.findUserByLogin(login));
		User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
		return (UserDetails) user;
	}

	public Optional<User> getUserByPhoneService(String phone)throws DataNotInDBException {
		return Optional.ofNullable(userRepository.findUserByPhone(phone).orElseThrow(()->new DataNotInDBException()));
	}

	public List<User> getRegisteredUsers() {
		return userRepository.findAll();
	}

	public UserDTO getUserById(Long id) {

		return userMapper.userToUserDTO(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
	}

	public User getUser(Long id){
		return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
	}

	public void userUpdateLoginDB(Long id, UserUpdateDTO dto) throws AssertionError {
		User user = userRepository.findById(id).orElse(null);
		if (user == null) throw new AssertionError();
		user.setLogin(dto.getLogin());
		userRepository.saveAndFlush(user);
	}

	public void userAccountUpdate(UserUpdateDTO dto, Integer id) {
		List<Role> roles = new ArrayList();
		roles.add(roleRepository.findByRole("USER"));
		roles.add(roleRepository.findByRole("ADMIN"));
		User user = userMapper.updateUser(dto, id);
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setState(stateRepository.findByState("CONFIRMED"));
		user.setRoles(roles);
		user.setStatuses(statusRepository.findByStatus("NEW"));
		userRepository.saveAndFlush(user);
	}

	@Transactional
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}

	public User authUserService(UserAuthDTO dto) {
		User user = (User) loadUserByUsername(dto.getLogin());
		if (dto.getPassword().equals(user.getPassword())) {
			userMapper.userAuthDTO(dto);
		}
		return user;
	}
}



//	private void checkCurrentUserUpdatePermission(Integer userId) throws CannotEditOtherUsersException {
//		UserDTO currentUser =getCurrentUser();
//		Optional<User> optionalUser = userRepository.findById(userId);
//				if (optionalUser.isPresent()&&!optionalUser.get().getPhone().equals(currentUser.getLogin())&&!currentUser.getRole().equals("ADMIN")){
//					throw new CannotEditOtherUsersException();
//				}
//	}
//	private UserDTO getCurrentUser() {
//		SecurityContext securityContext = SecurityContextHolder.getContext();
//		String login = securityContext.getAuthentication().getPrincipal().toString();
//		String role = securityContext.getAuthentication().getAuthorities().stream().findAny().get().getAuthority();
//		return new UserDTO(login, role);
//	}

