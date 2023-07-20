package bozhko_project.electronic_board.service;

import bozhko_project.electronic_board.dto.dto_ads.AnnouncementUpdateDto;
import bozhko_project.electronic_board.dto.dto_ads.CreateAnnouncementDto;
import bozhko_project.electronic_board.dto.dto_products.BrandDto;
import bozhko_project.electronic_board.dto.dto_products.CategoryDto;
import bozhko_project.electronic_board.dto.dto_products.CreateProductDto;
import bozhko_project.electronic_board.dto.dto_products.ProductUpdateDTO;
import bozhko_project.electronic_board.entities.ads.Announcement;
import bozhko_project.electronic_board.entities.products.Product;
import bozhko_project.electronic_board.exception.DataNotInDBException;
import bozhko_project.electronic_board.exception.UserNotFoundException;
import bozhko_project.electronic_board.mapper.AnnouncementMapper;
import bozhko_project.electronic_board.mapper.ProductMapper;
import bozhko_project.electronic_board.repository.ads_repository.AnnouncementRepository;
import bozhko_project.electronic_board.repository.products_repository.ProductRepository;
import bozhko_project.electronic_board.repository.users_repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Service
public class AnnouncementService {
    @Autowired
    private final AnnouncementRepository announcementRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final AnnouncementMapper announcementMapper;
    @Autowired
    private final ProductMapper productMapper;

    public List<Announcement> getListAdsService() {
        return announcementRepository.findAll();
    }

    public List<Announcement> getListAdsByUserService(Long userId) {
        return Collections.singletonList(announcementRepository.findAnnouncementByUserId(userId));
    }

    public List<Announcement> getListAdsByProductIdService(Long productId) {
        return announcementRepository.findAnnouncementByProductId(productId);
    }


    public Announcement getAnnouncementByIdService(Long announcementId)throws DataNotInDBException {
        return Optional.ofNullable(announcementRepository.getById(announcementId)).orElseThrow(()->new DataNotInDBException());
    }

    List<Announcement> getListAdsByTitleAdService(String titleAd) {
        return announcementRepository.findAnnouncementByTitle(titleAd);
    }

    public Announcement createAnnouncementService(CreateAnnouncementDto dto,
                                                  CreateProductDto productDto, BrandDto brandDto,
                                                  CategoryDto categoryDto, Long userId) {
        if (!userRepository.existsById(userId)){
            throw new UserNotFoundException();
        }
            Announcement announcement = announcementMapper.toCreateAnnouncementDTO(dto);
            Product product = productService.createProductService(productDto, brandDto, categoryDto);
            announcement.setTitle(dto.getTitle());
            announcement.setProduct(product);

            announcement.setUserId(userId);
            announcementRepository.save(announcement);
            return announcement;
    }

    public void announcementUpdateService(AnnouncementUpdateDto dto, ProductUpdateDTO productUpdateDTO, Long userId, Long productId, Long announcementId) throws DataNotInDBException, UsernameNotFoundException {
        if (!userRepository.existsById(userId)){
            throw new UserNotFoundException();
        }
        if (!productRepository.existsById(productId)|| !announcementRepository.existsById(announcementId)) {
            throw new DataNotInDBException();
        }
        Announcement announcement = announcementMapper.toAnnouncementUpdateDTO(dto);
        Product product = productMapper.productToUpdateDTO(productUpdateDTO, productId);
        announcement.setUserId(userId);
        announcement.setId(announcementId);
        announcement.setTitle(dto.getTitleAd());
        announcement.setProduct(product);
        announcementRepository.save(announcement);
    }

    public void deleteAnnouncementService(Long announcementId){
        announcementRepository.deleteById(announcementId);
    }
}




