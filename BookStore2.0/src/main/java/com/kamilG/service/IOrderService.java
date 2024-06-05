package com.kamilG.service;

import com.kamilG.model.Order;

public interface IOrderService {
    Order submitOrder();
    Order getOrderById(Long id);
    void saveOrder(Order order);
}
