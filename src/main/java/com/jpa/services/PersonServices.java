package com.jpa.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.models.Person;
import com.jpa.repositories.PersonRepository;

@CrossOrigin(origins = "*")
@RestController
public class PersonServices {
	
	@Autowired
	PersonRepository personRepository;
//	
//	@PostMapping("/api/register")
//	public Person register(@RequestBody Person person, HttpSession session) {
//		
//	//	person.setPassword(person.encryptPassword(user.getPassword()));
//		Person cp = personRepository.save(person);
//		
//		session.setAttribute("currentUser", cp);
//		
//		return cp;
//	}
	
	
	@PostMapping("/api/login")
	public Person login(@RequestBody Person person, HttpSession session) {
		person = personRepository.findPersonByCredentials(person.getEmail(), person.getPassword());
		session.setAttribute("currentPerson", person);
		return person;
		
	}
	
	@PostMapping("/api/logout")
	public void logout
	(HttpSession session) {
		session.invalidate();
	}



}
