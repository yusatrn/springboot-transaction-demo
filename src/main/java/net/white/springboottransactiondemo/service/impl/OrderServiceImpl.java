package net.white.springboottransactiondemo.service.impl;

import jakarta.transaction.Transactional;
import lombok.Getter;
import net.white.springboottransactiondemo.dto.OrderRequest;
import net.white.springboottransactiondemo.dto.OrderResponse;
import net.white.springboottransactiondemo.entity.Order;
import net.white.springboottransactiondemo.entity.Payment;
import net.white.springboottransactiondemo.exception.PaymentException;
import net.white.springboottransactiondemo.repository.OrderRepository;
import net.white.springboottransactiondemo.repository.PaymentRepository;
import net.white.springboottransactiondemo.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = orderRequest.getOrder();
        order.setStatus("INPROGRESS");
        orderRepository.save(order);
        Payment payment = orderRequest.getPayment();
        if (!payment.getType().equals("DEBIT")) {
            throw new PaymentException("Payment card type do not support");
        }
        payment.setOrderId(order.getId());
        paymentRepository.save(payment);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESSFUL");
        return orderResponse;
    }
}
