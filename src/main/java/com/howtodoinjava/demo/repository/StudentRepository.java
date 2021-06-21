package com.howtodoinjava.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.howtodoinjava.demo.model.StudentEntity;

@Repository
public interface StudentRepository
			extends CrudRepository<StudentEntity, Long> {

	@Query("select u from StudentEntity u where u.email = ?1")
	StudentEntity findByEmail(String email);


}
