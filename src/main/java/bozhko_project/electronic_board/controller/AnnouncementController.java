package bozhko_project.electronic_board.controller;

import bozhko_project.electronic_board.dto.dto_ads.AnnouncementUpdateDto;
import bozhko_project.electronic_board.dto.dto_ads.CreateAnnouncementDto;
import bozhko_project.electronic_board.dto.dto_products.BrandDto;
import bozhko_project.electronic_board.dto.dto_products.CategoryDto;
import bozhko_project.electronic_board.dto.dto_products.CreateProductDto;
import bozhko_project.electronic_board.dto.dto_products.ProductUpdateDTO;
import bozhko_project.electronic_board.entities.ads.Announcement;
import bozhko_project.electronic_board.exception.DataNotInDBException;
import bozhko_project.electronic_board.exception.Response;
import bozhko_project.electronic_board.exception.UserNotFoundException;
import bozhko_project.electronic_board.service.AnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/announcements")
@RequiredArgsConstructor
@Tag(name = "AnnouncementController", description = "API объявлений")
@Validated
public class AnnouncementController {
    @Autowired
    private final AnnouncementService announcementService;

    @Operation(description = "Добавление объявления")
    @PostMapping(value ="{userId}")
    public ResponseEntity<Announcement> createAnnouncementController(@Parameter(description = "запрос на добавление объявления",required = true)
                                                          @RequestBody(required = false) CreateAnnouncementDto request,
                                                                     CreateProductDto product, BrandDto brand,
                                                                     CategoryDto category,
                                                                     @PathVariable("userId") Long userId) throws UserNotFoundException {
        ;
        return ResponseEntity.ok(announcementService.createAnnouncementService(request, product, brand, category,userId));
    }

    @Operation(description = "Обновление объявления")
    @PutMapping(value = "{announcementId}")
    public ResponseEntity<AnnouncementUpdateDto> announcementUpdateController(@Parameter(description = "запрос на обновление объявления", required = true)
                                                                              @RequestBody(required = false) AnnouncementUpdateDto request,
                                                                              ProductUpdateDTO productUpdateDTO, Long userId, Long productId,
                                                                              @PathVariable("announcementId") Long announcementId)
            throws UserNotFoundException, DataNotInDBException {
        announcementService.announcementUpdateService(request,productUpdateDTO,userId,productId,announcementId);
        return ResponseEntity.ok(request);
    }

    @Operation(description = "Удаление объявления")
    @DeleteMapping(value = "{announcementId}")
    public ResponseEntity<Object> deleteProductController(@Parameter(description = "Идентификатор для удаления", required = true)
                                                          @PathVariable(value = "announcementId") Long announcementId){
        announcementService.deleteAnnouncementService(announcementId);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Получение списка объявлений")
    @GetMapping(produces = {"application/json"})
    public ResponseEntity <List<Announcement>> getListAllAnnouncementController(@Parameter(description = "Список объявлений")
                                                                                @RequestParam String announcements){
        List<Announcement> announcementList = announcementService.getListAdsService();
        return ResponseEntity.ok(announcementList);
    }
    @Operation(description = "Получение списка объявлений пользователя")
    @GetMapping(value = "users/{userId}",
            produces = {"application/json"})
    public ResponseEntity <List<Announcement>> getListAnnouncementByUserController(@Parameter(description = "Список объявлений")
                                                                                @PathVariable(value = "userId") Long userId){
        List<Announcement> adsListByUserId = announcementService.getListAdsByUserService(userId);
        return ResponseEntity.ok(adsListByUserId);
    }

    @Operation(description = "Получение объявления по ID")
    @GetMapping(value = "id/{announcementId}",
            produces = {"application/json"})
    public ResponseEntity<Announcement> getAnnouncementController(@Parameter(description = "Объявление")
                                                                                 @PathVariable(value = "announcementId") Long announcementId)
            throws DataNotInDBException{
    return ResponseEntity.ok(announcementService.getAnnouncementByIdService(announcementId));
    }

    @ExceptionHandler(DataNotInDBException.class)
    public Response handleException(DataNotInDBException e){
        return new Response(e.getMessage());
    }
    @ExceptionHandler(UserNotFoundException.class)
    public Response handleException(UserNotFoundException e){
        return new Response(e.getMessage());
    }

}
