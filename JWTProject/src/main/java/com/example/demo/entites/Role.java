package com.example.demo.entites;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="roles")
public class Role {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="name",nullable=false,length=20)
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(cascade= CascadeType.ALL,fetch= FetchType.EAGER,mappedBy="roles")
	private Set<User> users= new HashSet<User>();
	public void addUsers(User user) {
		user.getRoles().add(this);
		users.add(user);
	}
	public void deleteUsers(User user) {
		user.getRoles().remove(this);
		users.remove(user);
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
}
