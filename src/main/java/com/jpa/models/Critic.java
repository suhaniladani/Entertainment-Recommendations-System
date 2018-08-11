package com.jpa.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Critic extends Person{

	@OneToMany(mappedBy="critic")
	private List<Review> reviews;

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
	
	
	
	
}
