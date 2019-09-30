package com.example.PostawIWygraj.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "USER_NAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EMAIL")
    private String email;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "USER_ROLE", joinColumns = {
	    @JoinColumn(name = "USER_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
		    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") })
    @Column(name = "ROLES")
    private List<Role> roles;

    public User() {
    }
}
