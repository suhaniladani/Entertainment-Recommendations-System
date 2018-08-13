package com.jpa.services;

import java.util.ArrayList;
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

import com.jpa.models.Link;
import com.jpa.models.Movie;
import com.jpa.models.Seller;
import com.jpa.repositories.LinkRepository;
import com.jpa.repositories.MovieRepository;
import com.jpa.repositories.SellerRepository;

@CrossOrigin(origins = "*")
@RestController
public class LinkService {
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	LinkRepository linkRepository;
	
	@Autowired
	SellerRepository sellerRepository;
	
	@PostMapping("/api/seller/{sid}/movie/{imdbid}/link")
	public Link createLink(
			@PathVariable("imdbid") String imdbid,
			@PathVariable("sid") int sid,
			@RequestBody Link link) {
		Optional<Movie> omovie = movieRepository.findByImdbId(imdbid);
		Optional<Seller> oseller = sellerRepository.findById(sid);
		
		if(omovie.isPresent() && oseller.isPresent()) {
			Movie movie = omovie.get();
			Seller seller = oseller.get();
			link.setMovie(movie);
			link.setSeller(seller);
			return linkRepository.save(link);
		}
		return null;		
	}
	
	@DeleteMapping("/api/link/{lid}")
	public void deleteLink(@PathVariable("lid") int lid) {
		linkRepository.deleteById(lid);
	}
	
	@PutMapping("/api/link/{lid}")
	public void updateMovieLinks(
			@PathVariable int lid,
			@RequestBody Link link) {
			Optional<Link> olink = linkRepository.findById(lid);
			Link l = olink.get();
			l.setLink(link.getLink());
			linkRepository.save(l);
		
	}
	
	@GetMapping("/api/movie/{imdbid}/link")
	public List<Link> findAllLinksForMovie(
			@PathVariable("imdbid") String imdbid){
		Optional<Movie> omovie = movieRepository.findByImdbId(imdbid);
		if(omovie.isPresent()) {
			Movie movie = omovie.get();
			return movie.getBuyLink();
		}
		return null;
	}
	
	@GetMapping("/api/movie/{imdbid}/seller/{sid}/link")
	public Optional<Link> findAllLinksForMovieSeller(
			@PathVariable("imdbid") String imdbid,
			@PathVariable ("sid") int sid){
		Optional<Movie> omovie = movieRepository.findByImdbId(imdbid);
		Optional<Seller> oseller = sellerRepository.findById(sid);
		if(omovie.isPresent() && oseller.isPresent()) {
			Movie movie = omovie.get();
			Seller seller = oseller.get();
			return linkRepository.findLinkByMovieSeller(movie, seller);
		}
		return null;
	}
	
	@GetMapping("/api/seller/{sId}/linklist")
	public Iterable<Link> findLinksForSeller(
						@PathVariable("sId") int sId) {
		Optional<Seller> oseller = sellerRepository.findById(sId);
		if(oseller.isPresent()) {
			Seller seller = oseller.get();
			return seller.getLink();
			
		}
		return null;
	}
	
	@GetMapping("/api/link/{lid}")
	public Optional<Link> findLinkById(
			@PathVariable("lid") int lid) {
		return linkRepository.findById(lid);
	}

}
