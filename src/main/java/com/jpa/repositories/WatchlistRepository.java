package com.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jpa.models.Movie;
import com.jpa.models.Person;
import com.jpa.models.Watchlist;

public interface WatchlistRepository extends CrudRepository<Watchlist, Integer>{
	
	@Query("SELECT w FROM Watchlist w WHERE w.person=:person AND w.movie=:movie")
	Optional<Watchlist> findWatchlistByUserMovie (@Param("person") Person p, @Param("movie") Movie m);
	
	@Query("select b from Watchlist b JOIN b.movie c where c.id = :id ")
	Optional<Watchlist> findMovieByWatchlist (@Param("id") int id);
	

}
