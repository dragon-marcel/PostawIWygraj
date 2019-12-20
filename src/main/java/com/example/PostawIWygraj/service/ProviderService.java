package com.example.PostawIWygraj.service;

import java.util.List;

import com.example.PostawIWygraj.model.Provider;

public interface ProviderService {
    List<Provider> findAll();

    void save(Provider provider);

    void editProvider(Provider provider);

    Provider findById(Long id);

    Provider findByName(String name);

    void delete(Provider provider);
}
