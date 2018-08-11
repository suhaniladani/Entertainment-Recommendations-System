package com.jpa.services;


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
	

	@PostMapping("/api/review/movie/{mid}/critic/{cId}")
	public Review WriteReviewForMovie(
			@PathVariable("cId") int cId,
			@PathVariable("mId") int mId,
			@RequestBody Review review) {
		Optional<Critic> ocritic = criticRepository.findById(cId);
		
		Optional<Movie> omovie = movieRepository.findById(mId);
		
		
		if(ocritic.isPresent()) {
			
		System.out.println("critic found");
			Critic c = ocritic.get();
			
			if(omovie.isPresent()) {
				
				System.out.println("movie found");
				Movie movie = omovie.get();
				List<Review> reviews = movie.getReviews();
				reviews.add(review);
				List<Review> criticReviews =c.getReviews();
				criticReviews.add(review);
				criticRepository.save(c);
				return reviewRepository.save(review);
				
			} else {
			
				System.out.println("movie not found");
				Movie m1 = new Movie();

				movieRepository.save(m1);
				System.out.println("movie created");
				Optional<Movie> k = movieRepository.findById(mId);
				System.out.println("movie found after creating");
				List<Review> reviews = k.get().getReviews();
				reviews.add(review);
				List<Review> criticReviews =c.getReviews();
				criticReviews.add(review);
				criticRepository.save(c);
				System.out.println("return movies after saving");
				return reviewRepository.save(review);
			}
		}
		return null;
	}
	

}
