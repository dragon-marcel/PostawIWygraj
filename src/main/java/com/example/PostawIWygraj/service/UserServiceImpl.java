package com.example.PostawIWygraj.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.PostawIWygraj.model.Role;
import com.example.PostawIWygraj.model.User;
import com.example.PostawIWygraj.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleService roleService;
    @Autowired
    private SessionRegistry sessionRegistry;

    @Override
    public List<User> findAll() {
	return userRepository.findAll();
    }

    @Override
    public void save(User user) {
	Role role = roleService.findById(2L);
	List<Role> roles = new ArrayList<>();
	roles.add(role);
	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	user.setRoles(roles);
	userRepository.save(user);

    }

    @Override
    public User findByUsername(String username) {

	return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<UserDetails> getCurrentLoggedUsers() {
	List<Object> prencipals = sessionRegistry.getAllPrincipals().stream().collect(Collectors.toList());
	
	List<UserDetails> usersDetails = new ArrayList<>();
	
	for (Object principal : prencipals) {
	    if (principal instanceof UserDetails) {
		UserDetails user = (UserDetails) principal;
		usersDetails.add(user);
	    }
	}

	return usersDetails;

    }

    @Override
    public int getCountCurrentLoggedUsers() {
	return getCurrentLoggedUsers().size();
    }

    @Override
    public void editUser(User editeUser) {
	
	User user = findById(editeUser.getId());
	user.setName(editeUser.getName());
	user.setUsername(editeUser.getUsername());
	user.setSurname(editeUser.getSurname());
	user.setEmail(editeUser.getEmail());
	user.setWorkplace(editeUser.getWorkplace());

	user.setBlocked(editeUser.isBlocked());

	userRepository.save(user);
	
    }

    @Override
    public User findById(Long id) {
	return userRepository.findById(id).orElse(null);
    }

}
