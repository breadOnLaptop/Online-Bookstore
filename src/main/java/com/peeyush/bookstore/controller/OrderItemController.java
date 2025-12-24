package com.peeyush.bookstore.controller;

import com.peeyush.bookstore.entity.Book;
import com.peeyush.bookstore.entity.OrderItem;
import com.peeyush.bookstore.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping
    public OrderItem addItem(@RequestBody OrderItem orderItem) {
        return orderItemService.createOrderItem(orderItem);
    }

    @GetMapping("/order/{orderId}")
    public List<Book> getBooksByOrder(@PathVariable Long orderId) {
        return orderItemService.getBooksByOrder(orderId);
    }

    @GetMapping("/cart/{customerId}")
    public List<Book> getBooksInCart(@PathVariable Long customerId) {
        return orderItemService.getBooksInCustomerCart(customerId);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
    }
}
