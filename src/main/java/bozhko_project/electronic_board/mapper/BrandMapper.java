package bozhko_project.electronic_board.mapper;

import bozhko_project.electronic_board.dto.dto_products.BrandDto;
import bozhko_project.electronic_board.entities.products.Brand;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface BrandMapper {
    Brand toBrandDTO(BrandDto entity);
}
