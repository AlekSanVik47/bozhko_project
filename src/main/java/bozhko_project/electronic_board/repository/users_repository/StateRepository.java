package bozhko_project.electronic_board.repository.users_repository;

import bozhko_project.electronic_board.entities.users.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

	@Override
	Optional<State> findById(Long aLong);

	State findByState(String state);

}