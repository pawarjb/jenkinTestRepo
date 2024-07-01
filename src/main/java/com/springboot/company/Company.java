package com.springboot.company;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.job.Job;
import com.springboot.review.Review;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String descrption;
	
	
	//one company can have many/list of jobs
	@JsonIgnore
	@OneToMany(mappedBy = "company")
	private List<Job> jobs;

	@OneToMany(mappedBy = "company")
	private List<Review> reviews;


	public List<Review> getReviews() {
		return reviews;
	}


	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}


	public Company() {
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescrption() {
		return descrption;
	}


	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}


	public List<Job> getJobs() {
		return jobs;
	}


	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	
	
	
}
