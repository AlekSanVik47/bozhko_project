package bozhko_project.electronic_board.repository.products_repository;

import bozhko_project.electronic_board.entities.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByCategoryId(Long categoryId);

	List<Product> findByBrandId(Long brandId);
	List<Product> findByProductName(String productName);
	@Override
	Optional<Product> findById(Long aLong);

	@Override
	boolean existsById(Long aLong);

}