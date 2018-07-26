package com.jpa.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Critic extends Person{

	@OneToMany(mappedBy="critic")
	private List<Review> reviews;
	
	
}
