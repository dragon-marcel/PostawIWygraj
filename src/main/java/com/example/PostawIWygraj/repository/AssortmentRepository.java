package com.example.PostawIWygraj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PostawIWygraj.model.Assortment;
@Repository
public interface AssortmentRepository extends JpaRepository<Assortment, Long> {

}
