package com.example.PostawIWygraj.service;

import java.util.List;
import com.example.PostawIWygraj.model.Customer;

public interface CustomerService {
    List<Customer> findAll();

    void save(Customer customer);

    void editCustomer(Customer customer);

    Customer findById(Long id);

    void delete(Customer customer);

    Customer findByName(String name);
}
