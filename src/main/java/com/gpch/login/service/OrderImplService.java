package com.gpch.login.service;

import com.gpch.login.model.Orders;
import com.gpch.login.repository.OrderRepository;
import com.gpch.login.service.FactoryInterface.OrderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderImplService implements OrderInterface {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Orders createOrder(Orders orders) {
        return orderRepository.save(orders);
    }
}
