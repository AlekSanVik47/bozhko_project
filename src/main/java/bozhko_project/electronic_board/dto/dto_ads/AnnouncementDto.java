package bozhko_project.electronic_board.dto.dto_ads;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class AnnouncementDto implements Serializable {

	@Schema(description = "название объявления")
	private final String titleAd;
	@Schema(description = "Логин пользователя")
	private final String userIdLogin;
	@Schema(description = "Имя пользователя")
	private final String userIdName;
	@Schema(description = "Фамилия пользователя")
	private final String userIdSurname;
	@Schema(description = "email пользователя")
	private final String userIdEmail;
	@Schema(description = "телефон пользователя")
	private final String userIdPhone;
	@Schema(description = "ID продукта")
	private final Long productIdId;
	@Schema(description = "Наименование продукта")
	private final String productIdProductName;
	@Schema(description = "ID категории продукта")
	private final Long productIdCategoryId;
	@Schema(description = "описание")
	private final String productIdDescription;
	@Schema(description = "цена")
	private final double productIdPrice;
	@Schema(description = "ID брэнда продукта")
	private final Long productIdBrandId;
	@Schema(description = "ID изображения")
	private final Long productIdImageId;
}
