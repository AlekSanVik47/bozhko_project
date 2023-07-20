package bozhko_project.electronic_board.repository.products_repository;

import bozhko_project.electronic_board.entities.products.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Override
    Optional<Image> findById(Long aLong);

}