package com.training.courseservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.courseservice.intercomm.StudentClient;
import com.training.courseservice.model.Course;
import com.training.courseservice.model.Student;
import com.training.courseservice.repository.CourseRepository;

@RestController
@RequestMapping("/couapi")
public class CourseController {
	
	@Autowired
	CourseRepository repo;
	
	@Autowired
	StudentClient studClient;
	
	//Response Entity is a class which returns http status code along with data 
	
	@GetMapping("/course")
	public ResponseEntity<List<Course>> getCourse(){
		List<Course> courseList =  repo.findAll();
		return new ResponseEntity<>(courseList, HttpStatus.OK);
	}
	
	@PostMapping("/post")
	public ResponseEntity<Course> post(@RequestBody Course cou) {
		return new ResponseEntity<>(repo.save(cou), HttpStatus.CREATED);
	}
	
	@GetMapping("/course/{id}")
	public Course getCourseById(@PathVariable("id") long id) {
		Optional<Course> cou = repo.findById(id);
		
		if(cou.isPresent()) {
			return cou.get();
		} else {
			return null;
		}
	}
	
	@PutMapping("/course/{id}")
	public void updateCourse(@PathVariable("id") long id, @RequestBody Course cou) {
		
		Optional<Course> oldData = repo.findById(id);
		if(oldData.isPresent()) {
			Course cs = oldData.get();
			cs.setCourseName(cou.getCourseName());
			cs.setCourseDuration(cou.getCourseDuration());
			cs.setCourseFees(cou.getCourseFees());
			repo.save(cs);
		} else {
			System.out.println("No Data Found!");
		}
		
	}
	
	@DeleteMapping("/course/{id}")
	public void deleteCourse(@PathVariable("id") long id) {
		Optional<Course> delData = repo.findById(id);
		if(delData.isPresent()) {
			repo.deleteById(id);
		} else {
			System.out.println("No Data found!");
		}
	}
	
	@GetMapping("service/stud/{studid}")
	public Student getStud(@PathVariable("studid") long studId) {
		return studClient.getStudent(studId);
	}

}
