package com.peeyush.bookstore.controller;

import com.peeyush.bookstore.entity.Order;
import com.peeyush.bookstore.enums.OrderStatus;
import com.peeyush.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@Valid @RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping
    public List<Order> getAll() {
        return orderService.getAllOrders();
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getByCustomer(@PathVariable Long customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }

    @GetMapping("/recent")
    public List<Order> getRecent(@RequestParam(name = "days", defaultValue = "30") int days) {
        LocalDate since = LocalDate.now().minusDays(days);
        return orderService.getOrdersInLastDays(since);
    }

    @GetMapping("/status/{status}")
    public List<Order> getByStatus(@PathVariable OrderStatus status) {
        return orderService.getOrdersByStatus(status);
    }

    @GetMapping("/above")
    public List<Order> ordersAbove(@RequestParam Double amount) {
        return orderService.getOrdersAboveAmount(amount);
    }

    // Monthly sales report
    @GetMapping("/monthly-sales")
    public List<Object[]> monthlySales() {
        return orderService.getMonthlySalesReport();
    }

    @PutMapping("/{orderId}/status/{status}")
    public Order updateOrderStatus(
            @PathVariable Long orderId,
            @PathVariable OrderStatus status) {
        return orderService.updateOrderStatus(orderId, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/amount/{amount}")
    public List<Order> getOrdersAboveAmount(@PathVariable Double amount) {
        return orderService.getOrdersAboveAmount(amount);
    }
}
