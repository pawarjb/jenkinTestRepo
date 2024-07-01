package com.springboot.job;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService{

	//private List<Job> jobs = new ArrayList<Job>();
	//private Long nextId = 1L;
	@Autowired
	private JobRepository repo;
	
	@Override
	public List<Job> findAll() {

		return repo.findAll();
	}

	@Override
	public void createJob(Job job) {
		repo.save(job);
	}

	@Override
	public Job getJobById(Long id) {
		
		return repo.findById(id).orElse(null);
	}

	@Override
	public boolean deleteJobById(Long id) {
		try {
			repo.deleteById(id);
		return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateJob(Long id, Job updateJob) {

		 Optional<Job> jobOptional = repo.findById(id);
		
			if(jobOptional.isPresent()) {
				Job job = jobOptional.get();
				job.setTitle(updateJob.getTitle());
				job.setDescription(updateJob.getDescription());
				job.setMinSalary(updateJob.getMinSalary());
				job.setMaxSalary(updateJob.getMaxSalary());
				job.setLocation(updateJob.getLocation());
				repo.save(job);
				return true;
			}
		
		
		return false;
	}

}
