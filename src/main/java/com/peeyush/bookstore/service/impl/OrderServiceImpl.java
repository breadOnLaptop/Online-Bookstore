package com.peeyush.bookstore.service.impl;

import com.peeyush.bookstore.entity.Customer;
import com.peeyush.bookstore.entity.Order;
import com.peeyush.bookstore.enums.OrderStatus;
import com.peeyush.bookstore.repository.CustomerRepository;
import com.peeyush.bookstore.repository.OrderRepository;
import com.peeyush.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public Order createOrder(Order order) {

        if (order.getCustomer() == null || order.getCustomer().getId() == null) {
            throw new IllegalArgumentException("Customer ID is required");
        }

        Customer customer = customerRepository.findById(order.getCustomer().getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Customer not found with id " + order.getCustomer().getId())
                );

        order.setCustomer(customer);

        if (order.getOrderDate() == null) {
            order.setOrderDate(LocalDate.now());
        }

        if (order.getStatus() == null) {
            order.setStatus(OrderStatus.CREATED);
        }

        order.setTotalAmount(0.0);

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Order> getOrdersInLastDays(LocalDate afterDate) {
        return orderRepository.findByOrderDateAfter(afterDate);
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public List<Order> getOrdersAboveAmount(Double amount) {
        return orderRepository.findByTotalAmountGreaterThan(amount);
    }

    @Override
    public List<Object[]> getMonthlySalesReport() {
        return orderRepository.getMonthlySalesReport();
    }

    @Override
    @Transactional
    public Order updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = getOrderById(orderId);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }
}
