package com.peeyush.bookstore.repository;

import com.peeyush.bookstore.entity.Order;
import com.peeyush.bookstore.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId); // QUERY 1: Find all orders by customer
    List<Order> findByOrderDateAfter(LocalDate date); // QUERY 4: Find orders placed in last 30 days
    List<Order> findByStatus(OrderStatus status); // QUERY 7: Orders with status SHIPPED
    List<Order> findByTotalAmountGreaterThan(Double amount); // QUERY 10: Orders above a certain amount

    // QUERY 9: Monthly sales report
    @Query("""
            SELECT MONTH(o.orderDate), SUM(o.totalAmount)
            FROM Order o
            GROUP BY MONTH(o.orderDate)
            """)
    List<Object[]> getMonthlySalesReport();
}
