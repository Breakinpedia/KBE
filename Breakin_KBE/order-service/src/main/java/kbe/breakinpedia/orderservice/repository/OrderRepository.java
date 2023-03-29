package kbe.breakinpedia.orderservice.repository;

import kbe.breakinpedia.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
