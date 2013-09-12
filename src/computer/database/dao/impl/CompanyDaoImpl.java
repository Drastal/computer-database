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
			//Ici on appelle la namedQuery declaree en annotation dans la classe domain.User
			companies = em.createNamedQuery("findAllCompanies").getResultList();
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
		Company company = null;
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
