package com.jpa.models;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class User extends Person{
	
	@ManyToMany
	@JsonIgnore
	@JoinTable(name="USERTOMOVIE")
	private List<Movie> movies;
	
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String firstName, String lastName, String email, String password) {
		super(firstName, lastName, email, password);
		// TODO Auto-generated constructor stub
	}

	public void watchlistMovie(Movie movie) {
		if(movie != null) {
			this.movies.add(movie);
			if(!movie.getUsers().contains(this)) {
				movie.getUsers().add(this);
			}
		}
	
	}	

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public User(String firstName, String lastName, String email, String password, List<Movie> movies) {
		super(firstName, lastName, email, password);
		this.movies = movies;
	}


	


	

}
