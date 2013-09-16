package computer.database.dao;

import java.util.List;

import computer.database.domain.Machine;

public interface MachineDao {
	
	List<Machine> getMachines(String searching);
	List<Machine> getMachines(String searching, int resultPerPage, int pageNumber);
	Machine getMachine(long id);
	void editMachine(Machine machine);
	void deleteMachine(long id);
	
	void create(Machine machine);
}