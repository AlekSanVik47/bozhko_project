package bozhko_project.electronic_board.dto.dto_ads;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateAnnouncementDto implements Serializable {
    @Schema(description = "Название объявления")
    private final String title;

}
