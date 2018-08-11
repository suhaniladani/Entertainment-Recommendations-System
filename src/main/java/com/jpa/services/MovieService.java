package com.jpa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.models.Movie;
import com.jpa.repositories.MovieRepository;

@CrossOrigin(origins = "*")
@RestController
public class MovieService {
	
	@Autowired
	MovieRepository movieRepository;
	
	@GetMapping("/api/movie")
	public Iterable<Movie> findAllMovies(){
		return movieRepository.findAll();
		
	}
	
	@GetMapping("/api/movie/{movieId}")
	public Optional<Movie> findMovieById(@PathVariable("movieId") int id) {
		return movieRepository.findById(id);
	}
	
	@PostMapping("/api/movie")
	public Movie createMovie(@RequestBody Movie movie) {
		Optional<Movie> omovie = movieRepository.findById(movie.getId());
		if(omovie.isPresent()) {
			return null;
		}
		return movieRepository.save(movie);
	}
	


}
