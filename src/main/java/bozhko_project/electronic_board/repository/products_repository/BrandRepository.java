package bozhko_project.electronic_board.repository.products_repository;

import bozhko_project.electronic_board.entities.products.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findIdByBrand(String brand);

    @Override
    Optional<Brand> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    Brand findByBrand(String brand);

    boolean existsByBrand(String brand);


}