package com.howtodoinjava.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.StudentEntity;
import com.howtodoinjava.demo.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository repository;
	
	public List<StudentEntity> getAllStudents()
	{
		List<StudentEntity> result = (List<StudentEntity>) repository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<StudentEntity>();
		}
	}
	
	public StudentEntity getStudentsById(Long id) throws RecordNotFoundException
	{
		Optional<StudentEntity> student = repository.findById(id);
		
		if(student.isPresent()) {
			return student.get();
		} else {
			throw new RecordNotFoundException("No student record exist for given id");
		}
	}
	
	public StudentEntity createOrUpdateStudents(StudentEntity entity)
	{
		if(entity.getId()  == null) 
		{
			entity = repository.save(entity);
			
			return entity;
		} 
		else 
		{
			Optional<StudentEntity> student = repository.findById(entity.getId());
			
			if(student.isPresent())
			{
				StudentEntity newEntity = student.get();
				newEntity.setEmail(entity.getEmail());
				newEntity.setFirstName(entity.getFirstName());
				newEntity.setLastName(entity.getLastName());

				newEntity = repository.save(newEntity);
				
				return newEntity;
			} else {
				entity = repository.save(entity);
				
				return entity;
			}
		}
	} 
	
	public void deleteStudentsById(Long id) throws RecordNotFoundException
	{
		Optional<StudentEntity> student = repository.findById(id);
		
		if(student.isPresent())
		{
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No student record exist for given id");
		}
	} 
}