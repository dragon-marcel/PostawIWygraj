package com.example.PostawIWygraj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PostawIWygraj.model.Customer;
import com.example.PostawIWygraj.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Customer> findAll() {
	return customerRepository.findAll();
    }

    @Override
    public void save(Customer cusromer) {
	customerRepository.save(cusromer);
	
    }

    @Override
    public void editCustomer(Customer cusromer) {
	customerRepository.save(cusromer);
	
    }

    @Override
    public Customer findById(Long id) {
	// TODO Auto-generated method stub
	return 	customerRepository.findById(id).orElse(null);

    }

}
