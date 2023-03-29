package kbe.breakinpedia.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String productID;

    private String ProductName;
    private String description;
    private double price;
    private String author;
    private String imageUrl;
}
