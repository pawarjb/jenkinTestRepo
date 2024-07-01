package com.springboot.job;

import java.util.ArrayList;
import java.util.List;

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

@RestController
@RequestMapping("/jobs")
public class JobController {

	@Autowired
	private JobServiceImpl service;
	
	@GetMapping("/")
	public ResponseEntity<List>findAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@PostMapping("/")
	public ResponseEntity<String> createJob(@RequestBody Job job) {
		service.createJob(job);
		
		return new ResponseEntity<String>("job created successsfully",HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable Long id) {
		Job job = service.getJobById(id);
		if(job!=null) 
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
		
		boolean deleted = service.deleteJobById(id);
		if(deleted)
			return new ResponseEntity<>("Jobs deleted successfully", HttpStatus.OK);
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateJob(@PathVariable Long id , @RequestBody Job updateJob){
		boolean updated = service.updateJob(id, updateJob);
		if(updated) {
			return new ResponseEntity<>("Jobs Updated successfully", HttpStatus.OK);
			
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
