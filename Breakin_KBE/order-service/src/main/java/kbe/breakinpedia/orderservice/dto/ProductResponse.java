package kbe.breakinpedia.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private String productID;
    private String ProductName;
    private String description;
    private double price;
    private String author;
    private String imageUrl;
    private boolean isInProducts;
}
