package com.example.PostawIWygraj.model;

import java.math.BigDecimal;
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
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "USER_NAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Transient
    private String passwordConfirm;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "BLOCKED")
    private boolean blocked;
    @Column(name = "PAID")
    private BigDecimal paid;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "USER_ROLE", joinColumns = {
	    @JoinColumn(name = "USER_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
		    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") })
    @Column(name = "ROLES")
    private List<Role> roles;

    public User() {
	
    }
}
