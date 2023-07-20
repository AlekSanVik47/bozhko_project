package bozhko_project.electronic_board.repository.products_repository;

import bozhko_project.electronic_board.entities.products.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Long findIdByTitleCategory(String title);


    @Override
    Optional<Category> findById(Long aLong);

    Category findByTitleCategory(String titleCategory);


    boolean existsByTitleCategory(String titleCategory);
}