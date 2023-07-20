package bozhko_project.electronic_board.dto.dto_products;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProductUpdateDTO {

	@Schema(description = "Наименование товара")
	private final String productName;

	@Schema(description = "Описание")
	private final String description;

	@Schema(description = "Цена")
	private final double price;

	@Schema(description = "Изображение")
	private final Long imageId;
}
