package com.jpa.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/api/person")
	public List<Person> findAllperson() {
		return (List<Person>) personRepository.findAll();
	}

	@DeleteMapping("/api/person/{pid}")
	public void deletePersonById(
			@PathVariable("pid") int pid) {
		personRepository.deleteById(pid);
	}

	@GetMapping("/api/person/{pid}")
	public Person findPersonById(
			@PathVariable("pid") int pid) {
		Optional<Person> operson = personRepository.findById(pid);
		if(operson.isPresent()) {
			return operson.get();
		}
		return null;
	}
	
	@PutMapping("/api/person/{pid}")
	public Person updatePersonById(
			@PathVariable("pid") int pid,
			@RequestBody Person person) {
		Optional<Person> operson = personRepository.findById(pid);
		if (operson.isPresent()){
			Person p = operson.get();
			p.setFirstName(person.getFirstName());
			p.setLastName(person.getLastName());
			p.setEmail(person.getEmail());
			return p;
		}
		return null;
	}
	
	

}
