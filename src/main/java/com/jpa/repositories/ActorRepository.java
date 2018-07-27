package com.jpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jpa.models.Actor;
import com.jpa.models.User;

public interface ActorRepository extends CrudRepository<Actor, Integer>{
	

}
