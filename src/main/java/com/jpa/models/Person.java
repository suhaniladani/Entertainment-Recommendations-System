package com.jpa.models;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String obj;
//
//	@ManyToMany
//	@JoinTable(name="PERSON2CRITIC", 
//	joinColumns=@JoinColumn(name="personId"),
//	inverseJoinColumns = @JoinColumn(name = "criticId"))
//	private List<Person> person;
	
	@ManyToMany
	@JsonIgnore
	@JoinTable(name="PERSON2CRITIC")
	private List<Critic> critic;
	
	
	@OneToMany(mappedBy = "person", cascade=CascadeType.ALL)
	private List<Watchlist> watchlist;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public List<Watchlist> getWatchlist() {
		return watchlist;
	}

	public void setWatchlist(List<Watchlist> watchlist) {
		this.watchlist = watchlist;
	}
	
	public Person(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	public Person() {
		super();
	}
	
	public Person(String email, String password, String obj) {
		super();
		this.email = email;
		this.password = password;
		this.obj = obj;
	}
	
	public Object getObj() {
		return obj;
	}
	public void setObj(String obj) {
		this.obj = obj;
	}
	public List<Critic> getCritic() {
		return critic;
	}
	public void setCritic(List<Critic> critic) {
		this.critic = critic;
	}
	
	

	
	
}
