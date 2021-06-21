package com.howtodoinjava.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity

public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String firstName;
    
    @Column
    private String lastName;
    
    @Column(nullable=false, length=200)
    private String email;

    @Column(nullable=false, length=200)
    private String username;


	public StudentEntity() {
	}


}