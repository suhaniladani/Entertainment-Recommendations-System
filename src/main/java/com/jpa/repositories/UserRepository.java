package com.jpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jpa.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
//	@Query("SELECT u FROM User u WHERE u.username=:username")
//	Iterable<User> findUserByUsername (@Param("username") String u);
	
//	@Query("SELECT user FROM User user WHERE user.email=:email AND user.password=:password")
//	public User findUserByCredentials(@Param("email") String e, @Param("password") String p);
//			
}
