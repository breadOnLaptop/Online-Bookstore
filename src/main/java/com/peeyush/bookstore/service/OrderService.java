package com.peeyush.bookstore.service;

import com.peeyush.bookstore.entity.Order;
import com.peeyush.bookstore.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(Long id);
    List<Order> getAllOrders();
    List<Order> getOrdersByCustomerId(Long customerId);
    List<Order> getOrdersInLastDays(LocalDate afterDate);
    List<Order> getOrdersByStatus(OrderStatus status);
    List<Order> getOrdersAboveAmount(Double amount);
    List<Object[]> getMonthlySalesReport();
    Order updateOrderStatus(Long orderId, OrderStatus status);

    void deleteOrder(Long id);
}
