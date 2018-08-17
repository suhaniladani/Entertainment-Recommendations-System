package com.jpa.models;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class User extends Person{
	
//	@ManyToMany
//	@JsonIgnore
//	@JoinTable(name="USER2MOVIE")
//	private List<Movie> movies;
	
	@ManyToMany
	@JsonIgnore
	@JoinTable(name="USER2CRITIC")
	private List<Critic> critic;
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String firstName, String lastName, String email, String password) {
		super(firstName, lastName, email, password);
		// TODO Auto-generated constructor stub
	}

	public List<Critic> getCritic() {
		return critic;
	}

	public void setCritic(List<Critic> critic) {
		this.critic = critic;
	}

	


//	public void watchlistMovie(Movie movie) {
//		if(movie != null) {
//			this.movies.add(movie);
//			if(!movie.getUsers().contains(this)) {
//				movie.getUsers().add(this);
//			}
//		}
//	
//	}	

//	public List<Movie> getMovies() {
//		return movies;
//	}
//
//	public void setMovies(List<Movie> movies) {
//		this.movies = movies;
//	}



	


	

}
