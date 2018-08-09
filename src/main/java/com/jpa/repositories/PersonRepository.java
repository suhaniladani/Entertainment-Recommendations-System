package com.jpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jpa.models.Person;
import com.jpa.models.User;

public interface PersonRepository extends CrudRepository<Person, Integer>{
	

	@Query("SELECT person FROM Person person WHERE person.email=:email AND person.password=:password")
	public Person findPersonByCredentials(@Param("email") String e, @Param("password") String p);
			

}
