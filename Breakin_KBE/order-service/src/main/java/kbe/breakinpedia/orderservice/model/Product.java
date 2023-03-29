package kbe.breakinpedia.orderservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String productID;
    private String ProductName;
    private String description;
    private double price;
    private String imageUrl;
    private String author;
}
