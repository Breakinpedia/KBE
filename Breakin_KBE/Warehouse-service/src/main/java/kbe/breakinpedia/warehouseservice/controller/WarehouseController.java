package kbe.breakinpedia.warehouseservice.controller;

import kbe.breakinpedia.warehouseservice.config.CsvImporter;
import kbe.breakinpedia.warehouseservice.dto.ProductResponse;
import kbe.breakinpedia.warehouseservice.model.Product;
import kbe.breakinpedia.warehouseservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("api/warehouse")
@RequiredArgsConstructor
public class WarehouseController {
    private final ProductService productService;
    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable String id){
        return productService.getProductById(id);
    }

}
