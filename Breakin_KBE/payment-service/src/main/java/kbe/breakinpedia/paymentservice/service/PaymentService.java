package kbe.breakinpedia.paymentservice.service;

import kbe.breakinpedia.paymentservice.dto.PaymentRequest;
import kbe.breakinpedia.paymentservice.dto.PaymentResponse;
import kbe.breakinpedia.paymentservice.model.Payment;
import kbe.breakinpedia.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    @Autowired
    private final PaymentRepository paymentRepository;

    public Payment doPayment(PaymentRequest paymentRequest){
        Payment payment = new Payment();
        payment.setTransactionID(UUID.randomUUID().toString());
        payment.setPaymentStatus(paymentProcessing());
        payment.setOrderID(paymentRequest.getOrderId());
        payment.setTotalCost(paymentRequest.getTotalCost());
        payment.setCurrency(paymentRequest.getCurrency());
        paymentRepository.save(payment);
        return payment;
    }

    public String paymentProcessing(){
        //add payment methode hier after all like paypal, bank etc...
        return new Random().nextBoolean()?"success":"false";
    }

    public List<PaymentResponse> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream().map(payment -> mapToPaymentResponse(payment)).toList();
    }

    private PaymentResponse mapToPaymentResponse(Payment payment) {
        return PaymentResponse.builder()
                .paymentID(payment.getPaymentID())
                .paymentStatus(payment.getPaymentStatus())
                .transactionID(payment.getTransactionID())
                .orderID(payment.getOrderID())
                .totalCost(payment.getTotalCost())
                .currency(payment.getCurrency())
                .build();
    }
}
