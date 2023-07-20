package bozhko_project.electronic_board.repository.users_repository;

import bozhko_project.electronic_board.entities.users.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
	Status findByStatus(String status);

	@Override
	Optional<Status> findById(Long aLong);
}