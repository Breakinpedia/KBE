package Breakdance.KBEProdukt.controller;

import Breakdance.KBEProdukt.dto.ProductCheckResponse;
import Breakdance.KBEProdukt.dto.ProductRequest;
import Breakdance.KBEProdukt.dto.ProductResponse;
import Breakdance.KBEProdukt.model.Product;
import Breakdance.KBEProdukt.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * create new product
     * @param productRequest: add required data
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    /**
     * get all product in database
     * @return
     */
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean getProductById(@PathVariable String id){
        return productService.findID(id);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProductCheckResponse> isInProduct(@RequestParam List<String> id){
        return productService.isInProduct(id);
    }
}
