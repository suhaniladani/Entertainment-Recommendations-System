package com.jpa.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.OneToMany;

@Entity
public class Seller extends Person{
	
	
	@OneToMany(mappedBy = "seller", cascade=CascadeType.ALL)
	private List<Link> link;


	

	public List<Link> getLink() {
		return link;
	}

	public void setLink(List<Link> link) {
		this.link = link;
	}



	public Seller() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Seller(String firstName, String lastName, String email, String password) {
		super(firstName, lastName, email, password);
		// TODO Auto-generated constructor stub
	}
	
	
	

}
