package com.jpa.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Critic extends Person{

	@OneToMany(mappedBy="critic")
	@JsonIgnore
	private List<Review> reviews;
	
	@ManyToMany(mappedBy="critic", cascade=CascadeType.ALL)
	private List<User> user;

	public Critic(String firstName, String lastName, String email, String password, List<Review> reviews) {
		super(firstName, lastName, email, password);
		this.reviews = reviews;
	}

	public Critic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Critic(String firstName, String lastName, String email, String password) {
		super(firstName, lastName, email, password);
		// TODO Auto-generated constructor stub
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}
	
	
	
	
}
