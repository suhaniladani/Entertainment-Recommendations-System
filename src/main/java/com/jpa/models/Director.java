package com.jpa.models;



import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Director
 *
 */
@Entity
public class Director {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;

	@ManyToMany(mappedBy="directors", cascade=CascadeType.ALL)
	private List<Movie> moviesDirected;
	private int oscarWins;

	public Director() {
		super();
	}   
	
	public void directMovies(Movie movie) {
		this.moviesDirected.add(movie);
		if(!movie.getDirectors().contains(this)) {
			movie.getDirectors().add(this);
		}
	}	
	
	
	public List<Movie> getMoviesDirected() {
		return this.moviesDirected;
	}

	public void setMoviesDirected(List<Movie> moviesDirected) {
		this.moviesDirected = moviesDirected;
		for(Movie movie: moviesDirected) {
			movie.getDirectors().add(this);
		}
	}   
	public int getOscarWins() {
		return this.oscarWins;
	}

	public void setOscarWins(int oscarWins) {
		this.oscarWins = oscarWins;
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
   
}
