package com.example.PostawIWygraj.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PostawIWygraj.model.Order;
import com.example.PostawIWygraj.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
	return orderRepository.findAll();
    }

    @Override
    public void save(Order order) {
	orderRepository.save(order);

    }

    @Override
    public void editOrder(Order order) {
	orderRepository.save(order);

    }

    @Override
    public Order findById(Long id) {
	return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> findAllByIdUser(Long id) {
	return em.createQuery("from Order where user_id = ?1")
		.setParameter(1, id)
		.getResultList();
    }

}
