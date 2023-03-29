package kbe.breakinpedia.orderservice.dto.Payment;

import kbe.breakinpedia.orderservice.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Order order;
    private double totalCost;
    private String transactionID;
    private String message;
}