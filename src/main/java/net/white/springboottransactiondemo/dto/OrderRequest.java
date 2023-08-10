package net.white.springboottransactiondemo.dto;

import lombok.Getter;
import lombok.Setter;
import net.white.springboottransactiondemo.entity.Order;
import net.white.springboottransactiondemo.entity.Payment;
@Getter
@Setter
public class OrderRequest {
    private Order order;
    private Payment payment;
}
