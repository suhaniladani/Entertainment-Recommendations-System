package com.jpa.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.jpa.models.Movie;

@Entity
public class User extends Person{
	
	@ManyToMany(mappedBy="users", cascade=CascadeType.ALL)
	private List<Movie> movies = new ArrayList();
	
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String firstName, String lastName, String username, String password) {
		super(firstName, lastName, username, password);
		// TODO Auto-generated constructor stub
	}

	

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public User(String firstName, String lastName, String username, String password, List<Movie> movies) {
		super(firstName, lastName, username, password);
		this.movies = movies;
	}




	

}
