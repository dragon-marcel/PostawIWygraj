package com.example.PostawIWygraj.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PostawIWygraj.model.Assortment;
import com.example.PostawIWygraj.repository.AssortmentRepository;

@Service
public class AssortmentServiceImpl implements AssortmentService {

    @Autowired
    private AssortmentRepository assortmentRepository;
    @PersistenceContext
    private EntityManager em;

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

    @Override
    public Assortment findByName(String name) {

	List<Assortment> assortment = em.createQuery("from Assortment where name =UPPER('" + name + "')").getResultList();
	if (assortment.size() > 0) {
	    return assortment.stream().findFirst().get();
	} else {
	    return null;
	}
    }

    @Override
    public void delete(Assortment assortment) {
	assortmentRepository.delete(assortment);

    }

}
