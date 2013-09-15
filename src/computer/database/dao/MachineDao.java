package computer.database.dao;

import java.util.List;

import computer.database.domain.Machine;

public interface MachineDao {
	
	List<Machine> getMachines();
	List<Machine> getMachines(int resultPerPage, int pageNumber);
	List<Machine> getMachines(String searching);
	List<Machine> getMachines(String searching, int resultPerPage, int pageNumber);
	
	void create(Machine machine);
}