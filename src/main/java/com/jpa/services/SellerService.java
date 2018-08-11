package com.jpa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class SellerService {
	
	@Autowired
	SellerRepository sellerRepository;
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	LinkRepository linkRepository;
	
	@PostMapping("/api/seller/register")
	public Seller register(@RequestBody Seller seller, HttpSession session) {
		
		//user.setPassword(userDao.encryptPassword(user.getPassword()));
		Seller cs = sellerRepository.save(seller);
		
		session.setAttribute("currentSeller", cs);
		
		return cs;
	}

//	@PostMapping("/api/seller/{sId}/movie/{mid}")
//	public Link UserWatchlistMovie(
//			@PathVariable("sId") int sId,
//			@PathVariable("mid") int mid,
//			@RequestBody Link link) {
//	
//		
//		return linkRepository.save(link);
//	}
	
//	@PostMapping("/api/seller/{sId}/movie")
//	public List<Movie> SellerSellList(
//			@PathVariable("sId") int sId,
//			@RequestBody Movie a) {
//		Optional<Seller> oseller = sellerRepository.findById(sId);
//		
//		Optional<Movie> omovie = movieRepository.findByImdbId(a.getImdbid());
//		
//		List<Movie> empty = new ArrayList();
//		
//		if(oseller.isPresent()) {
//			
//		System.out.println("user found");
//			Seller s = oseller.get();
//			List<Movie> movies = s.getMovies();
//			
//			if(omovie.isPresent()) {
//				
//				System.out.println("movie found");
//				Movie movie = omovie.get();
//				if(movies.contains(movie)) {
//					return movies;
//				} else {
//					movies.add(movie);
//					s.setMovies(movies);
//					sellerRepository.save(s);
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
//				s.setMovies(movies);
//				//System.out.println("adding to watchlist");
//				//u.watchlistMovie(k.get());
//				//System.out.println("after watchlist");
//				System.out.println("movie added to list");
//				sellerRepository.save(s);
//				System.out.println("return movies after saving");
//				return movies;
//			}
//		}
//		return empty;
//	}
	
	
	@PostMapping("/api/seller/{sId}/movie/{imdbid}")
	public List<Movie> SellerSellList(
			@PathVariable("sId") int sId,
			@PathVariable("imdbid") String imdbid) {
		Optional<Movie> omovie = movieRepository.findByImdbId(imdbid);
		Optional<Seller> oseller = sellerRepository.findById(sId);
		if(oseller.isPresent() && omovie.isPresent()) {
			Seller seller = oseller.get();
			Movie movie = omovie.get();
			List<Movie> movies = seller.getMovies();
			if(movies.contains(movie)) {
				return movies;
			} else {
				movies.add(movie);
				seller.setMovies(movies);
				sellerRepository.save(seller);
				return movies;
			}
			
		}
		return null;
	}
	
	
	@GetMapping("/api/seller/{sId}/movielist")
	public Iterable<Movie> findMoviesToRent(
						@PathVariable("sId") int sId) {
		Optional<Seller> oseller = sellerRepository.findById(sId);
		if(oseller.isPresent()) {
			Seller seller = oseller.get();
			return seller.getMovies();
		}
		return null;
	}
	
	@PostMapping("/api/movie/{imdbid}/link")
	public Link createLink(
			@PathVariable("imdbid") String imdbid,
			@RequestBody Link link) {
		Optional<Movie> omovie = movieRepository.findByImdbId(imdbid);
		
		if(omovie.isPresent()) {
			Movie movie = omovie.get();
			link.setMovie(movie);
			return linkRepository.save(link);
		}
		return null;		
	}

}
