package com.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jpa.models.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer>{

}
