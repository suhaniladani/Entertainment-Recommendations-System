package com.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jpa.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
