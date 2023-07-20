package bozhko_project.electronic_board.controller;

import bozhko_project.electronic_board.dto.dto_user.UserCreationDTO;
import bozhko_project.electronic_board.dto.dto_user.UserUpdateDTO;
import bozhko_project.electronic_board.entities.users.User;
import bozhko_project.electronic_board.exception.DataNotInDBException;
import bozhko_project.electronic_board.exception.Response;
import bozhko_project.electronic_board.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Tag(name = "UserController", description = "API контролера пользователя")
@Validated
public class UserController {
    @Autowired
    private final UserServiceImpl usersService;

    @Operation(description = "Регистрация пользователя")
    @PostMapping
    public ResponseEntity<String> createUserController(@Parameter(description = "Запрос на создание пользователя", required = true)
                                             @RequestBody(required = false) UserCreationDTO request) {
        usersService.saveUser(request);
        return ResponseEntity.ok("login");
    }


    @Operation(description = "Удаление пользователя")
    @DeleteMapping(value = "{userId}")
    public ResponseEntity<Object> deleteUserController(@Parameter(description = "Идентификатор для удаления", required = true)
                                             @PathVariable(value = "userId") Long userId) {
        usersService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = " Получение списка зарегистрированных пользователей")
    @GetMapping(
            produces = {"application/json"}
    )
    public ResponseEntity<List<User>> getUsersController(@Parameter(description = "Список пользователей")
                                               @RequestParam String users) {
        List<User> allUsers = usersService.getRegisteredUsers();
        return ResponseEntity.ok(allUsers);
    }

    @Operation(description = " Получение пользователя по номеру телефона")
    @GetMapping(value = "phone/{phone}",
            produces = {"application/json"})
    public ResponseEntity<Optional<User>> getUserByPhoneController(@Parameter(description = "Поиск пользователя по номеру телефона")
                                                      @PathVariable(value = "phone") String phone)
            throws DataNotInDBException {
        return  ResponseEntity.ok(usersService.getUserByPhoneService(phone));

    }

    @Operation(description = "Обновление логина пользователя")
    @PutMapping(value = "login/{userId}")
    public ResponseEntity<String> updateUserLoginDBController(
            @Parameter(description = "Идентификатор пользователя", required = true)
            @PathVariable(value = "userId") Long userId,
            @RequestBody(required = false) UserUpdateDTO request) {
       usersService.userUpdateLoginDB(userId, request);
        return ResponseEntity.ok("Логин успешно обновлен");
    }

    @Operation(description = "Обновление данных пользователя")
    @PutMapping(value = "{userId}")
    public ResponseEntity<String> userUpdateDBController(
            @Parameter(description = "Идентификатор пользователя", required = true)
            @PathVariable("userId") Integer userId,
            @RequestBody(required = false) UserUpdateDTO request) {
        usersService.userAccountUpdate(request, userId);
        return ResponseEntity.ok("Данные успешно обновлены");
    }
    @ExceptionHandler({DataNotInDBException.class})
    public Response handleException(DataNotInDBException e){
        return new Response(e.getMessage());

    }
}
