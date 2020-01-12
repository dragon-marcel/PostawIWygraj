package com.example.PostawIWygraj.service;

import java.util.List;

import com.example.PostawIWygraj.model.Order;

public interface OrderService {
    List<Order> findAll();

    List<Order> getOrdersRaport(String start, String end,Long idUser);

    List<Order> findAllByIdUser(Long id);

    void save(Order order);

    void editOrder(Order order);

    Order findById(Long id);

    public String getNewNrOrder();
}
