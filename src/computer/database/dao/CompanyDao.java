package computer.database.dao;

import java.util.List;

import computer.database.domain.Company;

public interface CompanyDao {
	
	List<Company> getCompanies();

	void create(Company company);
}