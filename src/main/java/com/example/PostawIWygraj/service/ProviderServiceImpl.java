package com.example.PostawIWygraj.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PostawIWygraj.model.Provider;
import com.example.PostawIWygraj.repository.ProviderRepository;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepository providerRepository;
    @PersistenceContext
    private EntityManager em;

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

    @Override
    public Provider findByName(String name) {

	List<Provider> provider = em.createQuery("from Provider where name ='" + name + "'").getResultList();
	if (provider.size() > 0) {
	    return provider.stream().findFirst().get();
	} else {
	    return null;
	}
    }

    @Override
    public void delete(Provider provider) {
	providerRepository.delete(provider);

    }
}
