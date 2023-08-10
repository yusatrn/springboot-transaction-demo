package net.white.springboottransactiondemo.service;

import net.white.springboottransactiondemo.dto.OrderRequest;
import net.white.springboottransactiondemo.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
