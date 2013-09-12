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
			// Methode avec NamedQuery declaree en annotation dans la classe domain.Company
//			companies = em.createNamedQuery("findAllCompanies").getResultList();
			
			// Methode 2 avec transaction
			em.getTransaction().begin();
			Query q = em.createQuery("Select c From Company c");
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
	 public Company getCompany(long id){
		EntityManager em = null;
		Company company = new Company();
		List<Company> compList = null;
		try {
			em = DaoManager.INSTANCE.getEntityManager();
			em.getTransaction().begin();
			Query q = em.createQuery("SELECT c FROM Company c WHERE c.id=:id");
			q.setParameter("id", id);
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
	@Override
	public void create(Company user) {
//		EntityManager em = null;
//			try {
//				//Recuperation de l'entityManager qui gere la connexion a la BD
//				em = DaoManager.INSTANCE.getEntityManager();
//				//Debut de transaction (obligatoire pour des operations d'ecriture sur la BD)
//				em.getTransaction().begin();
//				
//				//Sauvegarde de l'utilisateur
//				em.persist(user);
//				
//				//Commit de la transaction = on applique toutes les operations ci dessus
//				em.getTransaction().commit();
//			} catch(Exception e) {
//				e.printStackTrace();
//			} finally {
//				if(em != null)
//					em.close();
//			}
	}
}
