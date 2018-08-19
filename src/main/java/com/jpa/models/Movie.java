package com.jpa.models;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;



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
	private String imdbid;

	@OneToMany(mappedBy="movie", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Review> reviews;

	
//	@ManyToMany(mappedBy="movies", cascade=CascadeType.ALL)
//	private List<User> users = null;
	
//	@ManyToMany(mappedBy="movies", cascade=CascadeType.ALL)
//	@JsonIgnore
//	private List<Seller> sellers = null;
	
	@OneToMany(mappedBy = "movie", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Link> buyLink;
	
	@OneToMany(mappedBy = "movie", cascade=CascadeType.ALL)
	private List<Watchlist> watchlist;

	public Movie() {
		super();
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

	
	
//	public List<User> getUsers() {
//		return users;
//	}
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}
	
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}


	public String getImdbid() {
		return imdbid;
	}
	public void setImdbId(String imdbid) {
		this.imdbid = imdbid;
	}


	public void setImdbid(String imdbid) {
		this.imdbid = imdbid;
	}

	public List<Link> getBuyLink() {
		return buyLink;
	}

	public void setBuyLink(List<Link> buyLink) {
		this.buyLink = buyLink;
	}



	
   
}
