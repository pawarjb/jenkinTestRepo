package com.springboot.company;

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
//define the mapping for this controller
@RequestMapping("/companies")
public class CompanyController {

	@Autowired
	private CompanyServiceImpl service;
	
	@GetMapping("/")
	public ResponseEntity<List<Company>> getCompanie(){
		return new ResponseEntity<>(service.getCompanies(),HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
		Company company = service.getCompanyById(id);
		if(company!=null) {
		return new ResponseEntity<Company>(company,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<String>updateCompany(@RequestBody Company company, @PathVariable Long id){
		service.updateCompany(company, id);
		
		return new ResponseEntity<>("Company updated successfully ", HttpStatus.OK);
	}
	@PostMapping("/")
	public ResponseEntity<String> createCompany(@RequestBody Company company){
		service.createCompany(company);
		return new ResponseEntity<>("Company Added successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String>deleteCompany(@PathVariable Long id){
		boolean isDeleted = service.deleteCompany(id);
		if(isDeleted) {
			return new ResponseEntity<String>("Company is deleted", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Company not found",HttpStatus.NOT_FOUND);
		}
	}
}
