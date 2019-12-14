package com.example.PostawIWygraj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PostawIWygraj.model.Order;

@Repository
public interface OrderRepository extends  JpaRepository<Order, Long>{

}
