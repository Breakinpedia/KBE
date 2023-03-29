package kbe.breakinpedia.orderservice.service;

import kbe.breakinpedia.orderservice.dto.OrderRequest;
import kbe.breakinpedia.orderservice.dto.OrderResponse;
import kbe.breakinpedia.orderservice.dto.Payment.Payment;
import kbe.breakinpedia.orderservice.dto.Payment.TransactionRequest;
import kbe.breakinpedia.orderservice.dto.Payment.TransactionResponse;
import kbe.breakinpedia.orderservice.dto.ProductRequest;
import kbe.breakinpedia.orderservice.model.Order;
import kbe.breakinpedia.orderservice.model.Product;
import kbe.breakinpedia.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    public TransactionResponse saveOrder(TransactionRequest transactionRequest){
        //create instanz
        Order order = new Order();
        OrderRequest orderRequest = transactionRequest.getOrderRequest();
        Payment payment = transactionRequest.getPayment();
        //add data in to order via body on orderRequest
        order.setOrderId(UUID.randomUUID().toString());
        List<Product> products = orderRequest.getProductsDto().stream()
                .map(this::mapToDto).toList();
        order.setProducts(products);
        order.setCustomerId(orderRequest.getCustomerId());
        order.setTotalCost(getSum(products));
        //setup for payment
        payment.setTotalCost(getSum(products));
        payment.setOrderId(order.getOrderId());
        //call Product-service to check if product is in Products database
        Payment paymentResponse = restTemplate.postForObject("http://PAYMENT-SERVICE/api/payment",payment, Payment.class);
        log.info("{}",payment);
        String response = paymentResponse.getPaymentStatus().equals("success")?"payment proccessing successfull and order placed":"payment processing failed!";
        //save order to database
        orderRepository.save(order);
        return new TransactionResponse(order, paymentResponse.getTotalCost(), paymentResponse.getTransactionID(), response);
    }

    private Product mapToDto(ProductRequest productRequest) {
        Product product = new Product();
        product.setPrice(productRequest.getPrice());
        product.setProductID(productRequest.getProductID());
        product.setProductName(productRequest.getProductName());
        product.setDescription(productRequest.getDescription());
        product.setAuthor(productRequest.getAuthor());
        product.setImageUrl(productRequest.getImageUrl());
        return product;
    }

    public void deleteOrder(String id) {
         orderRepository.deleteById(id);
    }

    public double getSum(List<Product> productsList){
        double sum = 0.0;
        for (Product product: productsList) {
            sum += product.getPrice();
        }
        return sum;
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::mapToOrderResponse).toList();
    }

    private OrderResponse mapToOrderResponse(Order order) {
        return OrderResponse.builder()
                .OrderId(order.getOrderId())
                .customerId(order.getCustomerId())
                .products(order.getProducts())
                .totalCost(order.getTotalCost())
                .build();
    }

    public Optional<Order> getOrderByOrderId(String orderId) {
        boolean result = orderRepository.findById(orderId).isPresent();
        if (result){
            return orderRepository.findById(orderId);
        }else{
            throw new IllegalArgumentException("there is no order with this order id!");
        }
    }

}
