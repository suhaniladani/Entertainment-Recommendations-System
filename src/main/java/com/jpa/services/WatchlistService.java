package com.jpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.models.Movie;
import com.jpa.models.Person;

import com.jpa.models.User;
import com.jpa.models.Watchlist;
import com.jpa.repositories.MovieRepository;
import com.jpa.repositories.PersonRepository;
import com.jpa.repositories.UserRepository;
import com.jpa.repositories.WatchlistRepository;

@CrossOrigin(origins = "*")
@RestController
public class WatchlistService {
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	WatchlistRepository watchlistRepository;
	
	@Autowired
	PersonRepository personRepository;
	
	@PostMapping("/api/movie/{imdbid}/person/{pid}/watchlist")
	public Watchlist createWatchlist(
			@PathVariable("imdbid") String imdbid,
			@PathVariable("pid") int pid) {
		Optional<Movie> omovie = movieRepository.findByImdbId(imdbid);
		Optional<Person> operson = personRepository.findById(pid);
		if(omovie.isPresent() && operson.isPresent()) {
			Movie movie = omovie.get();
			Person person = operson.get();
			Watchlist watchlist = new Watchlist();
			watchlist.setMovie(movie);
			watchlist.setPerson(person);
			return watchlistRepository.save(watchlist);
		}
		return null;		
	}
	
	@PutMapping("/api/movie/{imdbid}/person/{pid}/watchlist")
	public void watchlistedMovies(
			@PathVariable("imdbid") String imdbid,
			@PathVariable("pid") int pid) {
		Optional<Movie> omovie = movieRepository.findByImdbId(imdbid);
		Optional<User> operson = userRepository.findById(pid);
	
		if(omovie.isPresent() && operson.isPresent()) {
			Movie movie = omovie.get();
			Person person = operson.get();
			Optional<Watchlist> owatchlist = watchlistRepository.findWatchlistByUserMovie(person, movie);
			Watchlist watchlist = owatchlist.get();
			watchlist.setWatched(!watchlist.getWatched());
			watchlistRepository.save(watchlist);
		}
	}
	
	@GetMapping("/api/person/{pid}/watchlist")
	public List<Watchlist> findWatchlist(
						@PathVariable("pid") int pid) {
		Optional<Person> operson = personRepository.findById(pid);
		if(operson.isPresent()) {
			Person person = operson.get();
			return person.getWatchlist();
		}
		return null;
	}

}
