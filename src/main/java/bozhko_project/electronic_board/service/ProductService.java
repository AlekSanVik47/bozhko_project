package bozhko_project.electronic_board.service;

import bozhko_project.electronic_board.dto.dto_products.BrandDto;
import bozhko_project.electronic_board.dto.dto_products.CategoryDto;
import bozhko_project.electronic_board.dto.dto_products.CreateProductDto;
import bozhko_project.electronic_board.dto.dto_products.ProductUpdateDTO;
import bozhko_project.electronic_board.entities.products.Brand;
import bozhko_project.electronic_board.entities.products.Category;
import bozhko_project.electronic_board.entities.products.Product;
import bozhko_project.electronic_board.exception.DataNotInDBException;
import bozhko_project.electronic_board.mapper.BrandMapper;
import bozhko_project.electronic_board.mapper.CategoryMapper;
import bozhko_project.electronic_board.mapper.ProductMapper;
import bozhko_project.electronic_board.repository.products_repository.BrandRepository;
import bozhko_project.electronic_board.repository.products_repository.CategoryRepository;
import bozhko_project.electronic_board.repository.products_repository.ImageRepository;
import bozhko_project.electronic_board.repository.products_repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final BrandRepository brandRepository;
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final ProductMapper productMapper;
    @Autowired
    private final ImageRepository imageRepository;
    @Autowired
    private final BrandMapper brandMapper;
    @Autowired
    private final CategoryMapper categoryMapper;



    public List<Product> getListAllProductService() {
        return productRepository.findAll();
    }


    public List<Product> getProductByProductNameService(String productName)throws DataNotInDBException {
        if (productRepository.findByProductName(productName).isEmpty()){
            throw new DataNotInDBException();
        }
        return productRepository.findByProductName(productName);
    }

    public Optional<Product> getProductById(Long id) throws DataNotInDBException {
        return Optional.ofNullable(productRepository.findById(id).orElseThrow(()->new DataNotInDBException()));
    }

    public Brand checkOrSaveBrand(BrandDto dto){
        Brand brand = brandMapper.toBrandDTO(dto);
        if (!brandRepository.existsByBrand(dto.getBrand())) {
            brand.setBrand(dto.getBrand());
            return brandRepository.saveAndFlush(brand);
        }
        return brandRepository.findByBrand(dto.getBrand());
    }

    public Category checkCategory(CategoryDto dto) throws DataNotInDBException {
        Category category = categoryMapper.toCategoryDTO(dto);
        if (!categoryRepository.existsByTitleCategory(dto.getTitleCategory())) {
            throw new DataNotInDBException();
        }

        return categoryRepository.findByTitleCategory(dto.getTitleCategory());

    }
    public Product createProductService(CreateProductDto dto,
                                        BrandDto brandDto, CategoryDto categoryDto){
        Product product = productMapper.productToCreateProductDto(dto);
        Brand brand = checkOrSaveBrand(brandDto);
        Category category = checkCategory(categoryDto);
        product.setProductName(dto.getProductName());
        product.setBrand(brand);
        product.setCategory(category);
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        productRepository.save(product);
        return product;
    }

    public Product productUpdateService(ProductUpdateDTO dto, BrandDto brandDto, CategoryDto categoryDto, Long id) {
        Product product = productMapper.productToUpdateDTO(dto, id);
        Brand brand = checkOrSaveBrand(brandDto);
        Category category = checkCategory(categoryDto);
        product.setBrand(brand);
        product.setCategory(category);
        product.setImageId(dto.getImageId());
        product.setProductName(dto.getProductName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        productRepository.save(product);
        return product;
    }
    public void productNameUpdateService(ProductUpdateDTO dto, Long id) {
        Product product = productRepository.getById(id);
        product.setProductName(dto.getProductName());
        productRepository.saveAndFlush(product);
    }
    public void productDescriptionUpdateService(ProductUpdateDTO dto, Long id) {
        Product product = productRepository.getById(id);;
        product.setDescription(dto.getDescription());
        productRepository.saveAndFlush(product);
    }
    public void productPriceUpdateService(ProductUpdateDTO dto, Long id) {
        Product product = productRepository.getById(id);;
        product.setPrice(dto.getPrice());
        productRepository.saveAndFlush(product);
    }
    public void productImageUpdateService(ProductUpdateDTO dto, Long id) {
        Product product = productRepository.getById(id);
        product.setImageId(dto.getImageId());
        productRepository.saveAndFlush(product);
    }

    public void deleteProductService(Long id) {
        productRepository.deleteById(id);
    }

}
