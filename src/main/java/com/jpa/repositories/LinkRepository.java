package com.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jpa.models.Link;
import com.jpa.models.Movie;
import com.jpa.models.Seller;

public interface LinkRepository extends CrudRepository<Link, Integer>{
	
	@Query("SELECT l FROM Link l WHERE l.movie=:movie AND l.seller=:seller")
	Optional<Link> findLinkByMovieSeller (@Param("movie") Movie m, @Param("seller") Seller s);

}
