package computer.database.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import computer.database.dao.MachineDao;
import computer.database.dao.manager.DaoManager;
import computer.database.domain.Machine;

public class MachineDaoImpl implements MachineDao {

	public MachineDaoImpl() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Machine> getMachines() {

		EntityManager em = null;

		List<Machine> machines = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			//Ici on appelle la namedQuery declaree en annotation dans la classe domain.User
			machines = em.createNamedQuery("findAllMachines").getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(em != null)
				em.close();
		}
		return machines;
	}
	
	@Override
	public void create(Machine user) {
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
