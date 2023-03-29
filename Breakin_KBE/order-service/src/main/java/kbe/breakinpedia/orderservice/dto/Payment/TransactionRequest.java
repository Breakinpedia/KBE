package kbe.breakinpedia.orderservice.dto.Payment;

import kbe.breakinpedia.orderservice.dto.OrderRequest;
import kbe.breakinpedia.orderservice.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private OrderRequest orderRequest;
    private Payment payment;
}
