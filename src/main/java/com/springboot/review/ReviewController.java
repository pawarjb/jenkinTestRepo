package com.springboot.review;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

	@Autowired
	private ReviewServiceImpl service;
	
	@GetMapping("/reviews")
	public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
		
		return new ResponseEntity<>(service.getAllReviews(companyId),HttpStatus.OK);
	}
	
	@PostMapping("/reviews")
	public ResponseEntity<String> addReview(@RequestBody Review review, @PathVariable Long companyId) {
		
		 boolean isReviewSave = service.addReview(companyId, review);
		 if(isReviewSave) {
			 return new ResponseEntity<String>("Review added successfully", HttpStatus.OK);
		 }else {
			 return new ResponseEntity<String>("company not found",HttpStatus.NOT_FOUND);
		 }
		 }
	
	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId){
		
		return new ResponseEntity<Review>(service.getReview(companyId, reviewId),HttpStatus.OK);
	}
	
	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review){
		
		boolean isReviewUpdated = service.updateReview(companyId, reviewId, review);
		if(isReviewUpdated) {
			return new ResponseEntity<String>("Review updated", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Review not updated",HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<String>DeleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
		
		boolean isReviewDeleted = service.deleteReview(companyId,reviewId);
		if(isReviewDeleted) {
			return new ResponseEntity<String>("Review deleted", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Review not deleted",HttpStatus.NOT_FOUND);
		}
	}
}
