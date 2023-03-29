package kbe.breakinpedia.warehouseservice.repository;

import kbe.breakinpedia.warehouseservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
