package com.howtodoinjava.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.StudentEntity;
import com.howtodoinjava.demo.service.StudentService;

@Controller
@RequestMapping("/")
public class StudentMvcController
{


	@Autowired
	StudentService service;

//	List All Student
	@GetMapping("/home")
	public String getAllStudents(Model model)
	{
		List<StudentEntity> list = service.getAllStudents();

		model.addAttribute("students", list);
		return "list-students";
	}



	//	Create Student
//	@RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
	@PostMapping("/createOrUpdateProcess")
	public String createOrUpdateStudents(StudentEntity studentEntity)	{


		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encoadedPassword = bCryptPasswordEncoder.encode(studentEntity.getUsername());
		studentEntity.setUsername(encoadedPassword);

		service.createOrUpdateStudents(studentEntity);
		return "redirect:/home";
	}



	//	Edit Student
	@GetMapping({"/edit", "/edit/{id}"})
	public String editStudentsById(Model model, @PathVariable("id") Optional<Long> id)
							throws RecordNotFoundException 
	{
		if (id.isPresent()) {
			StudentEntity entity = service.getStudentsById(id.get());
			model.addAttribute("student", entity);
		} else {
			model.addAttribute("student", new StudentEntity());
		}
		return "add-edit-students";
	}



	//	Delete Student
	@GetMapping("/delete/{id}")
	public String deleteStudentsById(Model model, @PathVariable("id") Long id)
							throws RecordNotFoundException 
	{
		service.deleteStudentsById(id);
		return "redirect:/home";
	}


}
