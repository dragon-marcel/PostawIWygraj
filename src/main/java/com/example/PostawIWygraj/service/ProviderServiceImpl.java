package com.example.PostawIWygraj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PostawIWygraj.model.Provider;
import com.example.PostawIWygraj.repository.ProviderRepository;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public List<Provider> findAll() {

	return providerRepository.findAll();
    }

    @Override
    public void save(Provider provider) {
	providerRepository.save(provider);

    }

    @Override
    public void editProvider(Provider provider) {
	providerRepository.save(provider);

    }

    @Override
    public Provider findById(Long id) {
	return providerRepository.findById(id).orElse(null);
    }

}
