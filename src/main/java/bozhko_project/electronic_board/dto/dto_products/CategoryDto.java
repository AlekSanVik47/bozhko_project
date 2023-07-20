package bozhko_project.electronic_board.dto.dto_products;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDto implements Serializable {

	@Schema(description = "наименование категории товара")
	private final String titleCategory;
}
