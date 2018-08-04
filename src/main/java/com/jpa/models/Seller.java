package com.jpa.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Seller extends Person{
	
	@ManyToMany(mappedBy="sellers", cascade=CascadeType.ALL)
	private List<Movie> movies = new ArrayList();

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public Seller(String firstName, String lastName, String username, String password, List<Movie> movies) {
		super(firstName, lastName, username, password);
		this.movies = movies;
	}

	public Seller() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Seller(String firstName, String lastName, String username, String password) {
		super(firstName, lastName, username, password);
		// TODO Auto-generated constructor stub
	}
	
	
	

}
