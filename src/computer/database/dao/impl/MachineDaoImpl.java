package computer.database.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import computer.database.dao.MachineDao;
import computer.database.dao.manager.DaoManager;
import computer.database.domain.Machine;

public class MachineDaoImpl implements MachineDao {

	/*
	 * Op�rations sur les machines et requ�tes dans la table correspondante
	 */
	public MachineDaoImpl() {
	}
	
	@Override
	public void create(Machine machine) {
		//Cr�e une nouvelle machine en l'inscrivant dans la BDD
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
		//Retourne les machines dont le nom contient la suite de caract�res entr�e en param�tre
		EntityManager em = null;
		List<Machine> machines = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			Query q = null;
			if(searching==null||searching.trim().isEmpty())
				q = em.createNamedQuery("findAllMachines");//Recherche toutes les machines dans la BDD
			else
				q = em.createNamedQuery("searchMachine").setParameter("searching", "%"+searching+"%");//Recherche les machines dont le nom comporte la suite de caract�res entr�e en param�tre
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
		//Retourne la liste des ordinateurs r�pondant aux crit�res de recherche en prenant en compte la pagination (nombre de r�sultats limit�)
		EntityManager em = null;
        List<Machine> machines = null;

        try {
            em = DaoManager.INSTANCE.getEntityManager();
            Query q = null;
            if(searching==null||searching.trim().isEmpty())
            	q = em.createNamedQuery("findAllMachines");
            else
            	q = em.createNamedQuery("searchMachine").setParameter("searching", "%"+searching+"%");
            
            //Pour limiter le nombre de r�sultats de la requ�te Hibernate
            q.setFirstResult((pageNumber - 1) * resultPerPage);//Premier r�sultat de la liste � retourner
            q.setMaxResults(resultPerPage);//Nombre de r�sultats dans la liste � retourner

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
		//Retourne la machine r�pondant � l'Id fournit en param�tre d'entr�e
		EntityManager em = null;
        List<Machine> machine = null;

        try {
            em = DaoManager.INSTANCE.getEntityManager();
            Query q = em.createNamedQuery("matchMachineById").setParameter("machineId", id);//Recherche la machine r�pondant � l'Id fourni dans la BDD
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
