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
	

	@PostMapping("/api/movie/{imdbid}/critic/{cId}/review")
	public Review WriteReviewForMovie(
			@PathVariable("cId") int cId,
			@PathVariable("imdbid") String imdbid,
			@RequestBody Review review) {
		Optional<Critic> ocritic = criticRepository.findById(cId);
		Optional<Movie> omovie = movieRepository.findByImdbId(imdbid);
		if(ocritic.isPresent() && omovie.isPresent()) {
		Critic critic = ocritic.get();
		Movie movie = omovie.get();
		review.setCritic(critic);
		review.setMovie(movie);
		return reviewRepository.save(review);
			
		}
		return null;
	}
	
	@GetMapping("/api/movie/{imdbid}/review")
	public List<Review> findReviewsForMovie(
			@PathVariable String imdbid){
		Optional<Movie> omovie = movieRepository.findByImdbId(imdbid);
		if(omovie.isPresent()) {
			Movie movie = omovie.get();
			return movie.getReviews();
		}
		return null;
	}
	
	@DeleteMapping("/api/review/{rid}")
	public void deleteReview(
			@PathVariable("rid") int rid) {
		reviewRepository.deleteById(rid);
		
	}
	
	@PutMapping("/api/review/{rid}")
	public Review updateReview(
			@PathVariable("rid") int rid,
			@RequestBody Review review) {
		Optional<Review> oreview = reviewRepository.findById(rid);
		if(oreview.isPresent()) {
			Review review1 = oreview.get();
			review1.setTitle(review.getTitle());
			review1.setDescription(review.getDescription());
			return reviewRepository.save(review1);
		}
		return null;
	}
	
	
	@GetMapping("/api/critic/{cid}/review")
	public List<Review> findReviewsForCritic(
			@PathVariable int cid){
		Optional<Critic> ocritic = criticRepository.findById(cid);
		if(ocritic.isPresent()) {
			Critic critic = ocritic.get();
			return critic.getReviews();
		}
		return null;
	}
	
	@GetMapping("/api/review/{rid}")
	public Optional<Review> findReviewById(
			@PathVariable("rid") int rid) {
		return reviewRepository.findById(rid);
	}
	

}
