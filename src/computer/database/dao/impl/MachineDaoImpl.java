package computer.database.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import computer.database.dao.MachineDao;
import computer.database.dao.manager.DaoManager;
import computer.database.domain.Machine;

public class MachineDaoImpl implements MachineDao {

	/*
	 * Operations sur les machines et requetes dans la table correspondante
	 */
	public MachineDaoImpl() {
	}

	@Override
	public void create(Machine machine) {
		// Cree une nouvelle machine en l'inscrivant dans la BDD
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
		// Retourne les machines dont le nom contient la suite de caracteres
		// entree en parametre
		EntityManager em = null;
		List<Machine> machines = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			Query q = null;
			if (searching == null || searching.trim().isEmpty())
				q = em.createQuery("Select m From Machine m");// Recherche toutes les machines dans la BDD
			else
				q = em.createQuery("Select m From Machine m WHERE name LIKE :searching")
						.setParameter("searching", "%" + searching + "%");
			// Recherche les machines dont le nom comporte la suite de caracteres entree en parametre
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
	public List<Machine> getMachines(String searching, int resultPerPage,
			int pageNumber, String sortType, boolean ascending) {
		// Retourne la liste des ordinateurs repondant aux criteres de recherche
		// en prenant en compte la pagination (nombre de resultats limite)
		EntityManager em = null;
		List<Machine> machines = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			Query q = null;
			if (searching == null || searching.trim().isEmpty())
				if (sortType == null)
					q = em.createQuery("Select m From Machine m");
				else
					// Requete selon l'ordre voulu
					switch (sortType) {
					case "byCompany":
						if (ascending)
							q = em.createQuery("Select m From Machine m ORDER BY m.company.name ASC");
						else
							q = em.createQuery("Select m From Machine m ORDER BY m.company.name DESC");
						break;
					case "byIntroduced":
						if (ascending)
							q = em.createQuery("Select m From Machine m ORDER BY m.introduced ASC");
						else
							q = em.createQuery("Select m From Machine m ORDER BY m.introduced DESC");
						break;
					case "byDiscontinued":
						if (ascending)
							q = em.createQuery("Select m From Machine m ORDER BY m.discontinued ASC");
						else
							q = em.createQuery("Select m From Machine m ORDER BY m.discontinued DESC");
						break;
					default:
						if (ascending)
							q = em.createQuery("Select m From Machine m ORDER BY m.name ASC");
						else
							q = em.createQuery("Select m From Machine m ORDER BY m.name DESC");
						break;
					}

			else if (sortType == null)
				q = em.createQuery(
						"Select m From Machine m WHERE name LIKE :searching")
						.setParameter("searching", "%" + searching + "%");
			else
				// Requete selon l'ordre voulu
				switch (sortType) {
				case "byCompany":
					if (ascending)
						q = em.createQuery("Select m From Machine m WHERE name LIKE :searching ORDER BY m.company.name ASC")
								.setParameter("searching", "%" + searching + "%");
					else
						q = em.createQuery("Select m From Machine m WHERE name LIKE :searching ORDER BY m.company.name DESC")
								.setParameter("searching", "%" + searching + "%");
					break;
				case "byIntroduced":
					if (sortType.equals("byIntroduced"))
						if (ascending)
							q = em.createQuery("Select m From Machine m WHERE name LIKE :searching ORDER BY m.introduced ASC")
									.setParameter("searching", "%" + searching + "%");
						else
							q = em.createQuery("Select m From Machine m WHERE name LIKE :searching ORDER BY m.introduced DESC")
									.setParameter("searching", "%" + searching + "%");
					break;
				case "byDiscontinued":
					if (sortType.equals("byDiscontinued"))
						if (ascending)
							q = em.createQuery("Select m From Machine m WHERE name LIKE :searching ORDER BY m.discontinued ASC")
									.setParameter("searching", "%" + searching + "%");
						else
							q = em.createQuery("Select m From Machine m WHERE name LIKE :searching ORDER BY m.discontinued DESC")
									.setParameter("searching", "%" + searching + "%");
					break;
				default:
					if (ascending)
						q = em.createQuery("Select m From Machine m WHERE name LIKE :searching ORDER BY m.name ASC")
								.setParameter("searching", "%" + searching + "%");
					else
						q = em.createQuery("Select m From Machine m WHERE name LIKE :searching ORDER BY m.name DESC")
								.setParameter("searching", "%" + searching + "%");
				}

			// Pour limiter le nombre de resultats de la requete Hibernate
			q.setFirstResult((pageNumber - 1) * resultPerPage);// Premier resultat de la liste a retourner
			q.setMaxResults(resultPerPage);// Nombre de resultats dans la liste a retourner

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
		// Retourne la machine repondant a l'Id fournit en parametre d'entree
		EntityManager em = null;
		List<Machine> machine = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			Query q = em.createQuery(
					"SELECT m FROM Machine m WHERE m.id = :machineId")
					.setParameter("machineId", id);// Recherche la machine repondant a l'Id fourni dans la BDD
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

	@Override
	public void editMachine(Machine machine) {
		// Methode pour editer les donnees sur une machine
		EntityManager em = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			em.getTransaction().begin();
			// Trouve la machine a modifier par son ID
			Machine editingMachine = em.find(Machine.class, machine.getId());

			// Reinscrit les donnees de la machine dans la BDD
			editingMachine.setName(machine.getName());
			editingMachine.setIntroduced(machine.getIntroduced());
			editingMachine.setDiscontinued(machine.getDiscontinued());
			editingMachine.setCompany(machine.getCompany());

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
	}

	@Override
	public void deleteMachine(long id) {
		// Methode supprimant une machine de la BDD par son identifiant
		EntityManager em = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			em.getTransaction().begin();
			Machine machine = em.find(Machine.class, id);
			em.remove(machine);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
	}
}
