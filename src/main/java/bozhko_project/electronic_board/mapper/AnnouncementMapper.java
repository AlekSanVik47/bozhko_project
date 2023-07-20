package bozhko_project.electronic_board.mapper;

import bozhko_project.electronic_board.dto.dto_ads.AnnouncementUpdateDto;
import bozhko_project.electronic_board.dto.dto_ads.CreateAnnouncementDto;
import bozhko_project.electronic_board.dto.dto_products.ProductUpdateDTO;
import bozhko_project.electronic_board.entities.ads.Announcement;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface AnnouncementMapper {
	Announcement toCreateAnnouncementDTO(CreateAnnouncementDto entity);

	Announcement toAnnouncementUpdateDTO(AnnouncementUpdateDto entity);
}
