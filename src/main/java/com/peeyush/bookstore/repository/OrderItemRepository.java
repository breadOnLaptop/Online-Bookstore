package com.peeyush.bookstore.repository;

import com.peeyush.bookstore.entity.Book;
import com.peeyush.bookstore.entity.OrderItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("""
        SELECT oi.book
        FROM OrderItem oi
        WHERE oi.order.id = :orderId
    """)
    List<Book> findBooksByOrderId(Long orderId);

    @Query("""
        SELECT oi.book
        FROM OrderItem oi
        WHERE oi.order.customer.id = :customerId
          AND oi.order.status = 'CREATED'
    """)
    List<Book> findBooksInCustomerCart(Long customerId);
}
