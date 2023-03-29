package kbe.breakinpedia.warehouseservice.service;

import com.opencsv.CSVReader;
import kbe.breakinpedia.warehouseservice.dto.ProductResponse;
import kbe.breakinpedia.warehouseservice.model.Product;
import kbe.breakinpedia.warehouseservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;


import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductResponse> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::MaptoProductResponse).toList();
    }

    private ProductResponse MaptoProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .author(product.getAuthor())
                .imageUrl(product.getImageUrl())
                .build();
    }

    public Product getProductById(String id){
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("can not find any Product with this "+ id +"!!"));
    }

}
