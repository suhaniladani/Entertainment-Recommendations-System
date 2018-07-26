package com.jpa.models;


import javax.persistence.*;
import java.util.*;
/**
 * Entity implementation class for Entity: Actor
 *
 */
@Entity
public class Actor{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;

	

	@ManyToMany(mappedBy="actors", cascade=CascadeType.ALL)
	private List<Movie> moviesActed = new ArrayList();
	

	public void actMovies(Movie movie) {
		this.moviesActed.add(movie);
		if(!movie.getActors().contains(this)) {
			movie.getActors().add(this);
		}
	}	
	
	public List<Movie> getMoviesActed() {
		return moviesActed;
	}

	public void setMoviesActed(List<Movie> movies) {
		this.moviesActed = movies;
		for(Movie movie: movies) {
			movie.getActors().add(this);
		}
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Actor(String name, List<Movie> moviesActed) {
		super();
		this.name = name;
		this.moviesActed = moviesActed;
	}


	public Actor() {
		super();
	}
   
	
}
