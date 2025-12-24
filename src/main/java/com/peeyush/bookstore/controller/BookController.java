package com.peeyush.bookstore.controller;

import com.peeyush.bookstore.entity.Book;
import com.peeyush.bookstore.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public Book createBook(@Valid @RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PutMapping("/{id}")
    public Book updateBook(
            @PathVariable Long id,
            @Valid @RequestBody Book book
    ) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    // QUERY: Top 10 best-selling books
    @GetMapping("/top-selling")
    public List<Object[]> topSellingBooks() {
        return bookService.getTopSellingBooks();
    }

    // QUERY: Revenue per book
    @GetMapping("/revenue")
    public List<Object[]> revenuePerBook() {
        return bookService.getRevenuePerBook();
    }
}
