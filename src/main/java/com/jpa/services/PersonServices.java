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

import com.jpa.models.Critic;
import com.jpa.models.Person;
import com.jpa.models.User;
import com.jpa.repositories.CriticRepository;
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
	
	@Autowired
	CriticRepository criticRepository;
	
	
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
	public Iterable<Person> findAllperson() {
		return personRepository.findAll();
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
			System.out.println("firstName"+ person.getFirstName());
			p.setFirstName(person.getFirstName());
			p.setLastName(person.getLastName());
			p.setEmail(person.getEmail());
			p.setPassword(person.getPassword());
			return personRepository.save(p);
		}
		return null;
	}
	
	@PostMapping("/api/person/{pId}/critic/{cId}")
	public Person followCritic(
			@PathVariable("pId") int pId,
			@PathVariable("cId") int cId) {
		Optional<Critic> ocritic = criticRepository.findById(cId);
		Optional<Person> operson = personRepository.findById(pId);
		if(ocritic.isPresent() && operson.isPresent()) {
		Critic critic = ocritic.get();
		Person person = operson.get();
		List<Critic> c = person.getCritic();
		c.add(critic);
		person.setCritic(c);
		List<Person> p = critic.getPerson();
		p.add(person);
		critic.setPerson(p);
		criticRepository.save(critic);
		return personRepository.save(person);
			
		}
		return null;
	}
	
	@DeleteMapping("/api/person/{uid}/critic/{cid}")
	public void deleteCritic(
			@PathVariable("cid") int cid,
			@PathVariable("uid") int uid) {
	Optional<Critic> ocritic = criticRepository.findById(cid);
	Optional<Person> ouser = personRepository.findById(uid);
		List<Critic> c = ouser.get().getCritic();
		c.remove(ocritic.get());
		List<Person> u = ocritic.get().getPerson();
		u.remove(ouser.get());
		personRepository.save(ouser.get());
		criticRepository.save(ocritic.get());
	}
	
	@GetMapping("/api/person/{uId}/critic")
	public List<Critic> findCriticsfollowed(
			@PathVariable("uId") int uId) {
		Optional<Person> ouser = personRepository.findById(uId);
		if(ouser.isPresent()) {
			Person user = ouser.get();
			return user.getCritic();
		}
		return null;
	}
	

}
