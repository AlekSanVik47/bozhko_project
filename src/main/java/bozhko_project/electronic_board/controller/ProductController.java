package bozhko_project.electronic_board.controller;

import bozhko_project.electronic_board.dto.dto_products.BrandDto;
import bozhko_project.electronic_board.dto.dto_products.CategoryDto;
import bozhko_project.electronic_board.dto.dto_products.CreateProductDto;
import bozhko_project.electronic_board.dto.dto_products.ProductUpdateDTO;
import bozhko_project.electronic_board.entities.products.Product;
import bozhko_project.electronic_board.exception.CannotDeleteDataInUseException;
import bozhko_project.electronic_board.exception.DataNotInDBException;
import bozhko_project.electronic_board.exception.Response;
import bozhko_project.electronic_board.mapper.ProductMapper;
import bozhko_project.electronic_board.repository.products_repository.ProductRepository;
import bozhko_project.electronic_board.service.ProductService;
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
import java.util.Optional;

@Controller
@RestController
@RequestMapping("products")
@RequiredArgsConstructor
@Tag(name = "ProductController", description = "API продуктов")
@Validated
public class ProductController {
    @Autowired
    private final ProductService productService;
    @Autowired
    private final ProductMapper productMapper;
    @Autowired
    private final ProductRepository productRepository;


    @Operation(description = "Добавление/создание продукта")
    @PostMapping
    public ResponseEntity<Product> createProductController(@Parameter(description = "запрос на создание продукта",required = true)
                                                          @RequestBody(required = false)
                                                              CreateProductDto request, BrandDto brand, CategoryDto category)throws DataNotInDBException {
        return ResponseEntity.ok(productService.createProductService(request, brand, category));
    }
    @Operation(description = "Добавление/создание брэнда")
    @PostMapping("brands")
    public ResponseEntity<String> saveBrandController(@Parameter(description = "запрос на создание брэнда",required = true)
                                                          @RequestBody(required = false) BrandDto request){
        productService.checkOrSaveBrand(request);
        return ResponseEntity.ok("Брэнд добавлен в базу");
    }

    @Operation(description = "Обновление продукта")
    @PutMapping(value ="product/{productId}")
    public ResponseEntity<Product> productUpdateController(@Parameter(description = "запрос на обновление продукта",required = true)
                                                              @RequestBody(required = false) ProductUpdateDTO request,BrandDto brand, CategoryDto category,
    @PathVariable("productId") Long productId){
        ;
        return ResponseEntity.ok(productService.productUpdateService(request,brand, category, productId));
    }

    @Operation(description = "Обновление названия")
    @PutMapping(value ="productName/{productId}")
    public ResponseEntity<String> productNameUpdateController(@Parameter(description = "запрос на обновление продукта",required = true)
                                                          @RequestBody(required = false) ProductUpdateDTO request,
                                                          @PathVariable("productId") Long productId){
        productService.productNameUpdateService(request,productId );
        return ResponseEntity.ok("Название успешно обновлено");
    }
    @Operation(description = "Обновление изображения")
    @PutMapping(value ="image/{productId}")
    public ResponseEntity<String> productImageUpdateController(@Parameter(description = "запрос на обновление продукта",required = true)
                                                              @RequestBody(required = false) ProductUpdateDTO request,
                                                              @PathVariable("productId") Long productId){
        productService.productImageUpdateService(request,productId );
        return ResponseEntity.ok("Изображение успешно обновлено");
    }
    @Operation(description = "Обновление описания")
    @PutMapping(value ="description/{productId}")
    public ResponseEntity<String> productDescriptionUpdateController(@Parameter(description = "запрос на обновление продукта",required = true)
                                                               @RequestBody(required = false) ProductUpdateDTO request,
                                                               @PathVariable("productId") Long productId){
        productService.productDescriptionUpdateService(request,productId );
        return ResponseEntity.ok("Описание успешно обновлено");
    }
    @Operation(description = "Изменение цены")
    @PutMapping(value ="price/{productId}")
    public ResponseEntity<String> productPriceUpdateController(@Parameter(description = "запрос на обновление продукта",required = true)
                                                                     @RequestBody(required = false) ProductUpdateDTO request,
                                                                     @PathVariable("productId") Long productId){
        productService.productPriceUpdateService(request,productId );
        return ResponseEntity.ok("Цена успешно изменена");
    }

    @Operation(description = "Удаление продукта")
    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Object> deleteProductController(@Parameter(description = "Идентификатор для удаления",
            required = true) @PathVariable(value = "productId") Long productId) {
        productService.deleteProductService(productId);
            return ResponseEntity.noContent().build();
    }
    @Operation(description = "Получение списка продуктов")
    @GetMapping(produces = {"application/json"})
    public ResponseEntity<List<Product>> getListAllProductController(@Parameter(description = "Список продуктов")
                                                               @RequestParam String products){
        List<Product> productList = productService.getListAllProductService();
        return ResponseEntity.ok(productList);
    }
    @Operation(description = "Получение продукта по ID")
    @GetMapping(value = "id/{productId}",
            produces = {"application/json"})
    public ResponseEntity<Optional<Product>> getProductById(@Parameter(description = "Поиск продукта по ID")
                                                      @PathVariable(value = "productId") Long productId)
            throws DataNotInDBException{
        return ResponseEntity.ok(productService.getProductById(productId));
    }
    @Operation(description = "Получение продукта по названию")
    @GetMapping(value = "{productName}",
            produces = {"application/json"})
    public ResponseEntity<List<Product>> getProductByProductName(@Parameter(description = "Поиск продукта по названию")
                                                            @PathVariable(value = "productName") String productName)
            throws DataNotInDBException{
        return ResponseEntity.ok(productService.getProductByProductNameService(productName));

    }
@ExceptionHandler({DataNotInDBException.class})
public Response handleException(DataNotInDBException e){
    return new Response(e.getMessage());

}
@ExceptionHandler({CannotDeleteDataInUseException.class})
    public Response handleException(CannotDeleteDataInUseException e){
    return new Response(e.getMessage());
}


}
