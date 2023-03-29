package kbe.breakinpedia.orderservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    //@GeneratedValue
    private String OrderId;
    private String customerId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;
    private double totalCost;
}
