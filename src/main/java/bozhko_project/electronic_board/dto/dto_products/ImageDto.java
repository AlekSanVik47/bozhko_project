package bozhko_project.electronic_board.dto.dto_products;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class ImageDto implements Serializable {

    @Schema(description = "изображение")
    private final String image;
}
