package com.jpa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.models.Critic;
import com.jpa.models.Movie;
import com.jpa.models.Review;
import com.jpa.models.User;
import com.jpa.repositories.CriticRepository;
import com.jpa.repositories.MovieRepository;
import com.jpa.repositories.ReviewRepository;

@CrossOrigin(origins = "*")
@RestController
public class ReviewService {
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	CriticRepository criticRepository;
	

	@PostMapping("/api/review/movie/critic/{cId}")
	public Review WriteReviewForMovie(
			@PathVariable("cId") int cId,
			@RequestBody Movie a,
			@RequestBody Review review) {
		Optional<Critic> ocritic = criticRepository.findById(cId);
		
		Optional<Movie> omovie = movieRepository.findByImdbId(a.getImdbid());
		
		
		if(ocritic.isPresent()) {
			
		System.out.println("critic found");
			Critic c = ocritic.get();
			
			if(omovie.isPresent()) {
				
				System.out.println("movie found");
				Movie movie = omovie.get();
				
			} else {
			
				System.out.println("movie not found");
				Movie m1 = new Movie();
				m1.setTitle(a.getTitle());
				m1.setImdbId(a.getImdbid());
				movieRepository.save(m1);
				System.out.println("movie created");
				Optional<Movie> k = movieRepository.findByImdbId(a.getImdbid());
				System.out.println("movie found after creating");
				//System.out.println("adding to watchlist");
				//u.watchlistMovie(k.get());
				//System.out.println("after watchlist");
				criticRepository.save(c);
				System.out.println("return movies after saving");
				return reviewRepository.save(review);
			}
		}
		return null;
	}
	

}
