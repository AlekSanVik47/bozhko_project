package bozhko_project.electronic_board.mapper;

import bozhko_project.electronic_board.dto.dto_products.CategoryDto;
import bozhko_project.electronic_board.entities.products.Category;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface CategoryMapper {
    Category toCategoryDTO(CategoryDto title);
}
