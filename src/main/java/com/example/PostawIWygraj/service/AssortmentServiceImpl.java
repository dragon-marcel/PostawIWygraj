package com.example.PostawIWygraj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PostawIWygraj.model.Assortment;
import com.example.PostawIWygraj.repository.AssortmentRepository;

@Service
public class AssortmentServiceImpl implements AssortmentService {

    @Autowired
    private AssortmentRepository assortmentRepository;

    @Override
    public List<Assortment> findAll() {
	return assortmentRepository.findAll();
    }

    @Override
    public void save(Assortment assortment) {
	assortmentRepository.save(assortment);

    }

    @Override
    public void editAssortment(Assortment assortment) {
	assortmentRepository.save(assortment);

    }

    @Override
    public Assortment findById(Long id) {
	return assortmentRepository.findById(id).orElse(null);
    }

}
