package com.springboot.company;

import java.util.List;

public interface CompanyService {

	List<Company> getCompanies();
	boolean updateCompany(Company company, Long id);
	void createCompany(Company company);
	public boolean deleteCompany(Long id);
	public Company getCompanyById(Long id);
	
}
