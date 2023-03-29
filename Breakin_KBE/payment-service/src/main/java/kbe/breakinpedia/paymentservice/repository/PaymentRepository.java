package kbe.breakinpedia.paymentservice.repository;

import kbe.breakinpedia.paymentservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
