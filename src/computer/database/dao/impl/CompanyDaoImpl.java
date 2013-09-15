package computer.database.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import computer.database.dao.CompanyDao;
import computer.database.dao.manager.DaoManager;
import computer.database.domain.Company;

public class CompanyDaoImpl implements CompanyDao {
	public CompanyDaoImpl() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getCompanies() {

		EntityManager em = null;

		List<Company> companies = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			em.getTransaction().begin();
			Query q = em.createNamedQuery("findAllCompanies");
			companies = q.getResultList();
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(em != null)
				em.close();
		}
		return companies;
	}
	
	@SuppressWarnings("unchecked")
	public Company getCompany(long id){
		EntityManager em = null;
		Company company = new Company();
		List<Company> compList = null;
		try {
			em = DaoManager.INSTANCE.getEntityManager();
			em.getTransaction().begin();
			Query q = em.createNamedQuery("matchCompanyById").setParameter("id", id);
			compList = q.getResultList();
			company = compList.get(0);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		return company;
	 }
}
