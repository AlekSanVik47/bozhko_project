package bozhko_project.electronic_board.dto.dto_products;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDto implements Serializable {
    @Schema(description = "Наименование товара")
    private String productName;
    @Schema(description = "цена")
    private double price;
    @Schema(description = "описание")
    private String description;
}
