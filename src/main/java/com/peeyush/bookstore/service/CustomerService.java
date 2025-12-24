package com.peeyush.bookstore.service;

import com.peeyush.bookstore.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer createCustomer(Customer customer);

    Customer getCustomerById(Long id);

    List<Customer> getAllCustomers();

    Customer updateCustomer(Long id, Customer customer);

    void deleteCustomer(Long id);

    // Query based
    List<Customer> getCustomersWhoBoughtBook(Long bookId);
}
