package computer.database.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import computer.database.dao.MachineDao;
import computer.database.dao.manager.DaoManager;
import computer.database.domain.Machine;

public class MachineDaoImpl implements MachineDao {

	/*
	 * Opérations sur les machines et requêtes dans la table correspondante
	 */
	public MachineDaoImpl() {
	}
	
	@Override
	public void create(Machine machine) {
		//Crée une nouvelle machine en l'inscrivant dans la BDD
		EntityManager em = null;
		
		try {
			em = DaoManager.INSTANCE.getEntityManager();// Recuperation de l'entityManager qui gere la connexion a la BD
			em.getTransaction().begin();// Debut de transaction (obligatoire pour des operations d'ecriture sur la BD)
			em.persist(machine);// Sauvegarde de la machine
			em.getTransaction().commit();// Commit de la transaction = on applique toutes les operations ci dessus
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Machine> getMachines(String searching) {
		//Retourne les machines dont le nom contient la suite de caractères entrée en paramètre
		EntityManager em = null;
		List<Machine> machines = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			Query q = null;
			if(searching==null||searching.trim().isEmpty())
				q = em.createNamedQuery("findAllMachines");//Recherche toutes les machines dans la BDD
			else
				q = em.createNamedQuery("searchMachine").setParameter("searching", "%"+searching+"%");//Recherche les machines dont le nom comporte la suite de caractères entrée en paramètre
			machines = q.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(em != null)
				em.close();
		}
		return machines;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Machine> getMachines(String searching, int resultPerPage, int pageNumber) {
		//Retourne la liste des ordinateurs répondant aux critères de recherche en prenant en compte la pagination (nombre de résultats limité)
		EntityManager em = null;
        List<Machine> machines = null;

        try {
            em = DaoManager.INSTANCE.getEntityManager();
            Query q = null;
            if(searching==null||searching.trim().isEmpty())
            	q = em.createNamedQuery("findAllMachines");
            else
            	q = em.createNamedQuery("searchMachine").setParameter("searching", "%"+searching+"%");
            
            //Pour limiter le nombre de résultats de la requête Hibernate
            q.setFirstResult((pageNumber - 1) * resultPerPage);//Premier résultat de la liste à retourner
            q.setMaxResults(resultPerPage);//Nombre de résultats dans la liste à retourner

            machines = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null)
                em.close();
        }
        return machines;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Machine getMachine(long id) {
		//Retourne la machine répondant à l'Id fournit en paramètre d'entrée
		EntityManager em = null;
        List<Machine> machine = null;

        try {
            em = DaoManager.INSTANCE.getEntityManager();
            Query q = em.createNamedQuery("matchMachineById").setParameter("machineId", id);//Recherche la machine répondant à l'Id fourni dans la BDD
            machine = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null)
                em.close();
        }

        if (machine != null) {
            return machine.get(0);
        } else {
            return null;
        }
	}
}
