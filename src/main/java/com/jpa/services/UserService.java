package com.jpa.services;

import java.util.List;
import java.util.Optional;

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

	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
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

	
//	@GetMapping("/user")
//	public List<User> findAllUser(
//			@RequestParam(name="username", required=false) String username,
//			@RequestParam(name="password", required=false) String password
//			) {
//		if(username != null && password != null) {
//			return (List<User>) userRepository.findUserByCredentials(username, password);
//		} 
//		else if(username != null) {
//			return (List<User>) userRepository.findUserByUsername(username);
//		}
//		return (List<User>) userRepository.findAll();
//	}

}