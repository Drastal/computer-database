package computer.database.dao;

import java.util.List;

import computer.database.domain.Machine;

public interface MachineDao {
	
	/**
	 * Renvoie la liste des machines correspondant au mot recherche
	 * @param searching mot recherche
	 * @return Liste de machines
	 */
	List<Machine> getMachines(String searching);
	
	/**
	 * Renvoie la liste des machines correspondant au mot recherche
	 * Retourne le bon nombre de machines pour la pagination
	 * @param searching mot recherche
	 * @param resultPerPage nombre de resultats par page voulu
	 * @param pageNumber numero de page en court
	 * @param sortType type de tri a effectuer le cas echeant
	 * @param ascending true si a ordonner en sens croissant
	 * @return Liste de machines
	 */
	List<Machine> getMachines(String searching, int resultPerPage, int pageNumber, String sortType, boolean ascending);
	
	/**
	 * Renvoie la machine selon l'id donne
	 * @param id id de la machine recherchee
	 * @return
	 */
	Machine getMachine(long id);
	
	/**
	 * Edite les informations d'une machine
	 * @param machine machine a editer
	 */
	void editMachine(Machine machine);
	
	/**
	 * Supprime une machine
	 * @param id id de la machine a supprimer
	 */
	void deleteMachine(long id);
	
	/**
	 * Creer une nouvelle machine
	 * @param machine machine a creer
	 */
	void create(Machine machine);
}