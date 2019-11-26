package com.example.PostawIWygraj.service;

import java.util.List;
import com.example.PostawIWygraj.model.Assortment;

public interface AssortmentService {
    List<Assortment> findAll();

    void save(Assortment assortment);

    void editAssortment(Assortment assortment);

    Assortment findById(Long id);
}
