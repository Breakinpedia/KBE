package Breakdance.KBEProdukt.service;

import Breakdance.KBEProdukt.dto.ProductCheckResponse;
import Breakdance.KBEProdukt.dto.ProductRequest;
import Breakdance.KBEProdukt.dto.ProductResponse;
import Breakdance.KBEProdukt.model.Product;
import Breakdance.KBEProdukt.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    public Product createProduct(ProductRequest productRequest){
        //create instanz of product
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());

        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setName(productRequest.getName());
        product.setAuthor(productRequest.getAuthor());
        product.setImageUrl(productRequest.getImageUrl());
        log.info("product {} is saved!",product.getId());
        return productRepository.save(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::MaptoProductResponse).toList();
    }

    private ProductResponse MaptoProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .author(product.getAuthor())
                .build();
    }

    public boolean findID(String id) {
        return productRepository.findById(id).isPresent();
    }

    public List<ProductCheckResponse> isInProduct(List<String> id) {
        log.info("checking Product!!");
        return productRepository.getProductsByIdIn(id).stream()
                .map(product -> ProductCheckResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .isInProduct(findID(product.getId()))
                        .build()).toList();
    }
}
