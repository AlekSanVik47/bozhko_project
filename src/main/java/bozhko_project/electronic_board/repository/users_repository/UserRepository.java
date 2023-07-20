package bozhko_project.electronic_board.repository.users_repository;

import bozhko_project.electronic_board.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 Optional<User> findUserByPhone (String phone);

 @Override
 Optional<User> findById(Long aLong);

 @Override
 boolean existsById(Long aLong);

 @Transactional
 @Modifying
 @Query("delete from User u where u.id = ?1")
 void deleteById(Long id);

 User findUserByLogin(String login);


 @Transactional
 @Modifying
 @Query("update User u set u.login = ?1 where u.id = ?2")
 void loginUpdate(String login, Long id);

}