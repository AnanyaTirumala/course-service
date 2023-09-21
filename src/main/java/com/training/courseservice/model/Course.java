package com.training.courseservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Course_ID")
	private long courseId;
	@Column(name="Course_Name")
	private String courseName;
	@Column(name="Course_Duration")
	private int courseDuration;
	@Column(name="Course_Fees")
	private double courseFees;
	
	public Course (String name, int duration, double fees) {
		this.courseName=name;
		this.courseDuration=duration;
		this.courseFees=fees;
	}

}
