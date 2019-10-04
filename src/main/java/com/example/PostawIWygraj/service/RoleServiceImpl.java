package com.example.PostawIWygraj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PostawIWygraj.model.Role;
import com.example.PostawIWygraj.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
	return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
	return roleRepository.findById(id).orElse(null);
    }

}
