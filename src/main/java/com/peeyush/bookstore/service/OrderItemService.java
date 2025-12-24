package com.peeyush.bookstore.service;

import com.peeyush.bookstore.entity.Book;
import com.peeyush.bookstore.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItem createOrderItem(OrderItem orderItem);
    List<Book> getBooksByOrder(Long orderId);
    List<Book> getBooksInCustomerCart(Long customerId);

    void deleteOrderItem(Long id);
}
