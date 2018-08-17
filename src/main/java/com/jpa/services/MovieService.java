package com.jpa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
		Optional<Movie> m = movieRepository.findByImdbId(movie.getImdbid());
		if(m.isPresent()){
			return movieRepository.findByImdbId(m.get().getImdbid()).get();
		}
		else {
			return movieRepository.save(movie);
		}
			

	}
	
	@DeleteMapping("/api/movie/{imdbid}")
	public void deleteMovie(
			@PathVariable("imdbid") String imdbid) {
		Optional<Movie> movie = movieRepository.findByImdbId(imdbid);
		Movie m = movie.get();
		movieRepository.deleteById(m.getId());
	}
	


}
