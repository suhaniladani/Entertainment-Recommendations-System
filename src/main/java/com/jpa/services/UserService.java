package com.jpa.services;


import java.util.ArrayList;
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

import com.jpa.models.Movie;
//import com.jpa.daos.UserDao;
import com.jpa.models.User;
import com.jpa.repositories.MovieRepository;
import com.jpa.repositories.UserRepository;
//import com.jpa.security.WebSecurityConfig;


@CrossOrigin(origins = "*")
@RestController
public class UserService {

//	@Autowired
//	WebSecurityConfig webSecurity;
//	
//	@Autowired
//	UserDao userDao;
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/api/user/register")
	public User register(@RequestBody User user, HttpSession session) {
		
		//user.setPassword(userDao.encryptPassword(user.getPassword()));
		User cu = userRepository.save(user);
		
		session.setAttribute("currentUser", cu);
		
		return cu;
	}
	
	@GetMapping("/profile")
	public Optional<User> profile(HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		return userRepository.findById(currentUser.getId());
	}
	
//	@PostMapping("/login")
//	public User login(@RequestBody User user, HttpSession session) {
//		user = userRepository.findUserByCredentials(user.getEmail(), user.getPassword());
//		session.setAttribute("currentUser", user);
//		return user;
//	}

	
	@PutMapping("/api/user/{userId}")
	public User updateUser(
			@PathVariable("userId") int id,
			@RequestBody User newUser) {
		Optional<User> optional = userRepository.findById(id);
		if(optional.isPresent()) {
			User user = optional.get();
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			return userRepository.save(user);
		}
		return null;
	}
	
	@GetMapping("/api/user/{userId}")
	public Optional<User> findUserByUserId(@PathVariable("userId") String userId) {
		int id = Integer.parseInt(userId);
		return userRepository.findById(id);
	}
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	
	
	
	
//	@PostMapping("/api/user/{uId}/movie")
//	public List<Movie> UserWatchlistMovie(
//			@PathVariable("uId") int uId,
//			@RequestBody Movie a) {
//		Optional<User> ouser = userRepository.findById(uId);
//		
//		Optional<Movie> omovie = movieRepository.findByImdbId(a.getImdbid());
//		
//		List<Movie> empty = new ArrayList();
//		
//		if(ouser.isPresent()) {
//			
//		System.out.println("user found");
//			User u = ouser.get();
//			List<Movie> movies = u.getMovies();
//			
//			if(omovie.isPresent()) {
//				
//				System.out.println("movie found");
//				Movie movie = omovie.get();
//				if(movies.contains(movie)) {
//					return movies;
//				} else {
//					movies.add(movie);
//					u.setMovies(movies);
//					userRepository.save(u);
//					return movies;
//				}
//			} else {
//			
//				System.out.println("movie not found");
//				Movie m1 = new Movie();
//				m1.setTitle(a.getTitle());
//				m1.setImdbId(a.getImdbid());
//				movieRepository.save(m1);
//				System.out.println("movie created");
//				Optional<Movie> k = movieRepository.findByImdbId(a.getImdbid());
//				movies.add(k.get());
//				System.out.println("movie found after creating");
//				u.setMovies(movies);
//				//System.out.println("adding to watchlist");
//				//u.watchlistMovie(k.get());
//				//System.out.println("after watchlist");
//				System.out.println("movie added to list");
//				userRepository.save(u);
//				System.out.println("return movies after saving");
//				return movies;
//			}
//		}
//		return empty;
//	}
//	
//	
//	@GetMapping("/api/user/{uId}/watchlist")
//	public Iterable<Movie> findMoviesInWatchlist(
//						@PathVariable("uId") int uId) {
//		Optional<User> ouser = userRepository.findById(uId);
//		if(ouser.isPresent()) {
//			User user = ouser.get();
//			return user.getMovies();
//		}
//		return null;
//	}
}
		

		
		
		
//		if(ouser.isPresent() && omovie.isPresent()) {
//			System.out.println("inside if");
//			
//			System.out.println(omovie.get().getImdbid());
//	User user = ouser.get();
//	Movie movie = omovie.get();
//		user.watchlistMovie(movie);
//		userRepository.save(user);
//		}
		
//	}	
	
	
	
