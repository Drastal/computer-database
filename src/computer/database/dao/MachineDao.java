package computer.database.dao;

import java.util.List;

import computer.database.domain.Machine;

public interface MachineDao {
	
	List<Machine> getMachines();
	List<Machine> getMachines(String searching);
	
	void create(Machine machine);
}