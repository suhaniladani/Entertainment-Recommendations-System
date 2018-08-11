package com.jpa.daos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.jpa.models.Link;
import com.jpa.models.Movie;
import com.jpa.repositories.LinkRepository;
import com.jpa.repositories.MovieRepository;


@Component
public class LinkDao {
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	LinkRepository linkRepository;
	
	public Link createLink(
			@PathVariable("mid") int mid,
			@RequestBody Link link) {
		Optional<Movie> omovie = movieRepository.findById(mid);
		
		if(omovie.isPresent()) {
			Movie movie = omovie.get();
			link.setMovie(movie);
			return linkRepository.save(link);
		}
		return null;		
	}

}
