package bozhko_project.electronic_board.dto.dto_products;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class BrandDto implements Serializable {

	@Schema(description = "наименование брэнда товара")
	private final String brand;
}
