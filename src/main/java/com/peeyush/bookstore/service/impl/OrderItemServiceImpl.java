package com.peeyush.bookstore.service.impl;

import com.peeyush.bookstore.entity.*;
import com.peeyush.bookstore.repository.*;
import com.peeyush.bookstore.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public OrderItem createOrderItem(OrderItem orderItem) {

        Order order = orderRepository.findById(orderItem.getOrder().getId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        Book book = bookRepository.findById(orderItem.getBook().getId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        int qty = orderItem.getQuantity();
        if (qty <= 0) {
            throw new IllegalArgumentException("Quantity must be > 0");
        }

        if (book.getStock() < qty) {
            throw new IllegalArgumentException("Insufficient stock");
        }

        orderItem.setOrder(order);
        orderItem.setBook(book);
        orderItem.setPrice(book.getPrice());

        OrderItem savedItem = orderItemRepository.save(orderItem);

        // stock update
        book.setStock(book.getStock() - qty);
        bookRepository.save(book);

        // order total update
        order.setTotalAmount(order.getTotalAmount() + qty * book.getPrice());
        orderRepository.save(order);

        return savedItem;
    }

    @Override
    public java.util.List<Book> getBooksByOrder(Long orderId) {
        return orderItemRepository.findBooksByOrderId(orderId);
    }

    @Override
    public java.util.List<Book> getBooksInCustomerCart(Long customerId) {
        return orderItemRepository.findBooksInCustomerCart(customerId);
    }

    @Override
    public void deleteOrderItem(Long id) {
        OrderItem item = orderItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("OrderItem not found"));

        Order order = item.getOrder();
        Book book = item.getBook();

        // rollback totals
        order.setTotalAmount(
                order.getTotalAmount() - (item.getPrice() * item.getQuantity())
        );

        // restore stock
        book.setStock(book.getStock() + item.getQuantity());

        orderRepository.save(order);
        bookRepository.save(book);
        orderItemRepository.delete(item);
    }
}
