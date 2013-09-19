package computer.database.dao;

import java.util.List;

import computer.database.domain.Company;

public interface CompanyDao {
	
	/**
	 * Renvoie la liste complete des compagnies
	 * @return Liste des compagnies
	 */
	List<Company> getCompanies();
	
	/**
	 * Renvoie la compagnie selon l'id donne
	 * @param id id de la compagnie recherchee
	 * @return la compagnie recherchee
	 */
	Company getCompany(long id);
}