package kbe.breakinpedia.orderservice.dto.Payment;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private Long paymentID;
    private String paymentStatus;
    private String transactionID;
    private String orderId;
    private double totalCost;
    private String currency;
}
