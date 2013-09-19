package computer.database.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import computer.database.dao.CompanyDao;
import computer.database.dao.manager.DaoManager;
import computer.database.domain.Company;

/*
 * Operations sur la table des compagnies
 */
public class CompanyDaoImpl implements CompanyDao {
	public CompanyDaoImpl() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getCompanies() {
		//Fonction retournant la liste de toutes les compagnies apres interrogation de la BDD
		EntityManager em = null;
		List<Company> companies = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			em.getTransaction().begin();
			Query q = em.createQuery("Select c From Company c");//Recherche toutes les compagnies
			companies = q.getResultList();//Traduit le resultat de la requete en liste des compagnies
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
		//Fonction retournant la compagnie repondant a l'Id fourni en parametre d'entree
		EntityManager em = null;
		List<Company> company = null;
		
		try {
			em = DaoManager.INSTANCE.getEntityManager();
			em.getTransaction().begin();
			Query q = em.createQuery("SELECT c FROM Company c WHERE c.id=:id")
					.setParameter("id", id);//Recherche des compagnies correspondant a l'Id
			company = q.getResultList();//Traduit le resultat de la recherche dans la BDD en liste
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		if (company != null) {
            return company.get(0);
        } else {
            return null;
        }
	 }
}
