package bozhko_project.electronic_board.mapper;

import bozhko_project.electronic_board.dto.dto_products.CreateProductDto;
import bozhko_project.electronic_board.dto.dto_products.ProductUpdateDTO;
import bozhko_project.electronic_board.entities.products.Product;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface ProductMapper {

    Product productToCreateProductDto(CreateProductDto product);

    Product productToUpdateDTO(ProductUpdateDTO dto, Long id);
}
