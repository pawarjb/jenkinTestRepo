package com.springboot.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.company.Company;
import com.springboot.company.CompanyServiceImpl;

@Service
public  class ReviewServiceImpl implements ReviewService{

	@Autowired
	private ReviewRepository repo;
	
	@Autowired
	private CompanyServiceImpl companyService;
	
	@Override
	public List<Review> getAllReviews(Long companyId) {

		List<Review> reviews = repo.findByCompanyId(companyId);
		
		return reviews;
	}

	@Override
	public boolean addReview(Long id, Review review) {
		
		Company company = companyService.getCompanyById(id);
		if(company!=null) {
			review.setCompany(company);
			repo.save(review);
			return true;
		}else {
			return false;
		}
		
		
	}

	@Override
	public Review getReview(Long companyId, Long reviewId) {
		 List<Review> reviews = repo.findByCompanyId(companyId);
		
		return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
	}

	@Override
	public boolean updateReview(Long companyId, Long reviewId, Review review) {

		if(companyService.getCompanyById(companyId)!=null) {
			review.setCompany(companyService.getCompanyById(companyId));
			review.setId(reviewId);
			repo.save(review);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteReview(Long companyId, Long reviewId) {
	if(	companyService.getCompanyById(companyId)!=null && repo.existsById(reviewId)) {
		
		Review review = repo.findById(reviewId).orElse(null);
		Company company = review.getCompany();
		company.getReviews().remove(review);
		review.setCompany(null);
		companyService.updateCompany(company, companyId);
		repo.deleteById(reviewId);
		return true;
	}else {
		return false;
	}
	}

}
