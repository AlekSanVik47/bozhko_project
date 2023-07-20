package bozhko_project.electronic_board.mapper;

import bozhko_project.electronic_board.dto.dto_user.UserAuthDTO;
import bozhko_project.electronic_board.dto.dto_user.UserCreationDTO;
import bozhko_project.electronic_board.dto.dto_user.UserDTO;
import bozhko_project.electronic_board.dto.dto_user.UserUpdateDTO;
import bozhko_project.electronic_board.entities.users.User;
import org.mapstruct.*;

import static org.mapstruct.ReportingPolicy.IGNORE;


@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface UserMapper{

    UserAuthDTO userAuthDTO(UserAuthDTO dto);


    User userCreationToUser(UserCreationDTO dto);


    @AfterMapping
    default void afterMappingFromCreate(@MappingTarget User target, UserCreationDTO dto){
        target.getState();
    }


    User updateUser(UserUpdateDTO dto, Integer id);


    UserDTO userToUserDTO(User byId);
}


