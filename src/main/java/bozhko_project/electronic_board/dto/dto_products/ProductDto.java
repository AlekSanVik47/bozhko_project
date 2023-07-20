package bozhko_project.electronic_board.dto.dto_products;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDto implements Serializable {

    @Schema(description = "ID продукта")
    private final Long productId;

    @Schema(description = "Наименование товара")
    private final String productName;

    @Schema(description = "ID категории товара")
    private final Long categoryId;

    @Schema(description = "цена товара")
    private final double price;

    @Schema(description = "ID брэнда товара")
    private final Long brandId;

    @Schema(description = "описание товара")
    private final String description;
}
