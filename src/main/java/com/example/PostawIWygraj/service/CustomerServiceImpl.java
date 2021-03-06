package com.example.PostawIWygraj.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PostawIWygraj.model.Customer;
import com.example.PostawIWygraj.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Customer> findAll() {
	return customerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
	customerRepository.save(customer);

    }

    @Override
    public void editCustomer(Customer customer) {
	customerRepository.save(customer);

    }

    @Override
    public Customer findById(Long id) {
	return customerRepository.findById(id).orElse(null);

    }

    @Override
    public void delete(Customer customer) {
	customerRepository.delete(customer);

    }

    @Override
    public Customer findByName(String name) {

	List<Customer> customers = em.createQuery("from Customer where name = UPPER('" + name + "')").getResultList();
	if (customers.size() > 0) {
	    return customers.stream().findFirst().get();
	} else {
	    return null;
	}

    }

    @Override
    public List<Customer> search(String term) {
	List<Customer> customers = em.createQuery("from Customer where UPPER(name) like UPPER('%" + term + "%')").getResultList();
	return customers;
    }

}
