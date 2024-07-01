package com.springboot.company;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyRepository repo;
	
	@Override
	public List<Company> getCompanies() {
		return repo.findAll();
	}

	@Override
	public boolean updateCompany(Company company, Long id) {
			Optional<Company> companyObj = repo.findById(id);
			if(companyObj.isPresent()) {
				Company companyToUpdate = companyObj.get();
				companyToUpdate.setName(company.getName());
				companyToUpdate.setDescrption(company.getDescrption());
				companyToUpdate.setJobs(company.getJobs());
				repo.save(companyToUpdate);
				return true;
			}else {
				return false;
			}
	}

	@Override
	public void createCompany(Company company) {

		repo.save(company);
	}

	@Override
	public boolean deleteCompany(Long id) {

	if(repo.existsById(id)) {
		repo.deleteById(id);
		return true;
		}else {
		return false;
		}
	}

	@Override
	public Company getCompanyById(Long id) {

		return repo.findById(id).orElse(null);
	}

}
