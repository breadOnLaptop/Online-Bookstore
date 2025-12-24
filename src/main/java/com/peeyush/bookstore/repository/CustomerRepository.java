package com.peeyush.bookstore.repository;

import com.peeyush.bookstore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // QUERY 6: Customers who bought a specific book
    @Query("""
            SELECT DISTINCT o.customer
            FROM Order o
            JOIN o.orderItems oi
            WHERE oi.book.id = :bookId
            """)
    List<Customer> findCustomersWhoBoughtBook(Long bookId);
}
