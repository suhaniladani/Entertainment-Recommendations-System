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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.models.User;
import com.jpa.repositories.UserRepository;

@CrossOrigin(origins = "*")
@RestController
public class UserService {

	@Autowired
	UserRepository userRepository;

//	@PostMapping("/api/user")
//	public User createUser(@RequestBody User user) {
//		return userRepository.save(user);
//	}
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping("/user/{userId}")
	public Optional<User> findUserById(@PathVariable("userId") int id) {
		return userRepository.findById(id);
	}
	
	@PutMapping("/api/user/{userId}")
	public User updateUser(
			@PathVariable("userId") int id,
			@RequestBody User newUser) {
		Optional<User> optional = userRepository.findById(id);
		if(optional.isPresent()) {
			User user = optional.get();
			user.setFirstName(newUser.getFirstName());
			return userRepository.save(user);
		}
		return null;
	}


	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpSession session) {
		session.setAttribute("currentUser", user);
		return userRepository.save(user);
	}
	

	@GetMapping("/api/username")
	public List<User> findUserByUsername(@RequestParam(name="username", required=false) String username) {
			return (List<User>) userRepository.findUserByUsername(username);

	}
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session) {
	User currentUser = (User)
	session.getAttribute("currentUser");	
	return currentUser;
	}
	
	@PostMapping("/api/logout")
	public void logout
	(HttpSession session) {
		session.invalidate();
	}

	List<User> users = (List<User>) userRepository.findAll();
	
	@PostMapping("/api/login")
	public User login(@RequestBody User credentials,
	HttpSession session) {
	 for (User user : users) {
	  if( user.getUsername().equals(credentials.getUsername()) &&
	      user.getPassword().equals(credentials.getPassword())) {
	   session.setAttribute("currentUser", user);
	   return user;
	  }
	 }
	 return null;
	}

	
	

}