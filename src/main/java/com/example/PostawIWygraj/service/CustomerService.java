package com.example.PostawIWygraj.service;

import java.util.List;
import com.example.PostawIWygraj.model.Customer;

public interface CustomerService {
    List<Customer> findAll();

    void save(Customer cusromer);

    void editCustomer(Customer cusromer);

    Customer findById(Long id);
}
