package com.jpa.models;


import java.lang.String;
import java.util.List;

import javax.persistence.*;



/**
 * Entity implementation class for Entity: Movie
 *
 */
@Entity
public class Movie{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;

	@OneToMany(mappedBy="movie")
	private List<Review> reviews;

	
	@ManyToMany
	@JoinTable(name="MOVIE2ACTOR")
	private List<Actor> actors = null;
	
	@ManyToMany
	@JoinTable(name="MOVIE2DIRECTOR")
	private List<Director> directors = null;

	
	@ManyToMany
	@JoinTable(name="MOVIE2USER")
	private List<User> users = null;
	
	@ManyToMany
	@JoinTable(name="MOVIE2SELLER")
	private List<Seller> sellers = null;

	public Movie() {
		super();
	}   
	public Movie(String title) {
		this.title = title;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Actor> getActors() {
		return actors;
	}
	public void setActors(List<Actor> actors) {
		this.actors = actors;
		for(Actor actor: actors) {
			actor.getMoviesActed().add(this);
		}
	}
	public List<Director> getDirectors() {
		return directors;
	}
	
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public List<Seller> getSellers() {
		return sellers;
	}
	public void setSellers(List<Seller> sellers) {
		this.sellers = sellers;
	}
	public void setDirectors(List<Director> directors) {
		this.directors = directors;
		for(Director director: directors) {
			director.getMoviesDirected().add(this);
		}
	}
   
}
