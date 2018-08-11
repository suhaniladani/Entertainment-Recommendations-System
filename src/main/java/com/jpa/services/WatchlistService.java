package com.jpa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.models.Movie;
import com.jpa.models.User;
import com.jpa.models.Watchlist;
import com.jpa.repositories.MovieRepository;
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
	
	@PostMapping("/api/movie/{imdbid}/user/{uid}/watchlist")
	public Watchlist createWatchlist(
			@PathVariable("imdbid") String imdbid,
			@PathVariable("uid") int uid,
			@RequestBody Watchlist watchlist) {
		Optional<Movie> omovie = movieRepository.findByImdbId(imdbid);
		Optional<User> ouser = userRepository.findById(uid);
		if(omovie.isPresent() && ouser.isPresent()) {
			Movie movie = omovie.get();
			User user = ouser.get();
			watchlist.setMovie(movie);
			watchlist.setUser(user);
			return watchlistRepository.save(watchlist);
		}
		return null;		
	}

}
