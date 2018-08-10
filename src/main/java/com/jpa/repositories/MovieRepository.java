package com.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jpa.models.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer>{
	
	@Query("SELECT m FROM Movie m WHERE m.imdbid=:imdbid")
	Optional<Movie> findByImdbId (@Param("imdbid") String m);

}
