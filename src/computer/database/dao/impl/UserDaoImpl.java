package computer.database.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.formation.jee.dao.UserDao;
import com.formation.jee.dao.manager.DaoManager;
import com.formation.jee.domain.User;

public class UserDaoImpl implements UserDao {

	public UserDaoImpl() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {

		EntityManager em = null;

		List<User> users = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			//Ici on appelle la namedQuery declaree en annotation dans la classe domain.User
			users = em.createNamedQuery("findAllUsers").getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(em != null)
				em.close();
		}
		return users;
	}
	
	@Override
	public void create(User user) {
		EntityManager em = null;
			try {
				//Recuperation de l'entityManager qui gere la connexion a la BD
				em = DaoManager.INSTANCE.getEntityManager();
				//Debut de transaction (obligatoire pour des operations d'ecriture sur la BD)
				em.getTransaction().begin();
				
				//Sauvegarde de l'utilisateur
				em.persist(user);
				
				//Commit de la transaction = on applique toutes les operations ci dessus
				em.getTransaction().commit();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(em != null)
					em.close();
			}
	}
}
