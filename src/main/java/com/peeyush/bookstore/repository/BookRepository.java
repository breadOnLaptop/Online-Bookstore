package com.peeyush.bookstore.repository;

import com.peeyush.bookstore.entity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // QUERY 2: Top 10 best-selling books
    @Query("""
    SELECT oi.book, SUM(oi.quantity)
    FROM OrderItem oi
    GROUP BY oi.book
    ORDER BY SUM(oi.quantity) DESC
  """)
    List<Object[]> findTopSellingBooks(Pageable pageable);

    // QUERY 5: Total revenue per book
    @Query("""
    SELECT oi.book.title, SUM(oi.price * oi.quantity)
    FROM OrderItem oi
    GROUP BY oi.book.title
  """)
    List<Object[]> getRevenuePerBook();
}
