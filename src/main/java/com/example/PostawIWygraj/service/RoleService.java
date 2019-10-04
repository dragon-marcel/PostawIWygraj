package com.example.PostawIWygraj.service;

import java.util.List;

import com.example.PostawIWygraj.model.Role;

public interface RoleService {
    List<Role> findAll();
    Role findById(Long id);
}
