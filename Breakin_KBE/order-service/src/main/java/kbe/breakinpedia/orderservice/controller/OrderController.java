package kbe.breakinpedia.orderservice.controller;

import kbe.breakinpedia.orderservice.dto.OrderResponse;
import kbe.breakinpedia.orderservice.dto.Payment.TransactionRequest;
import kbe.breakinpedia.orderservice.dto.Payment.TransactionResponse;
import kbe.breakinpedia.orderservice.model.Order;
import kbe.breakinpedia.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor

public class OrderController{
    @Autowired
    private final OrderService orderService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse saveOrder(@RequestBody TransactionRequest transactionRequest){
        return orderService.saveOrder(transactionRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteOrder(@PathVariable String id ){
        orderService.deleteOrder(id);
        return "Order with "+id+" is deleted";
    }
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Order> getOrderById(@PathVariable String id){
        return  orderService.getOrderByOrderId(id);
    }
}
