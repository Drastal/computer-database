package computer.database.service;

import java.util.List;

import computer.database.domain.Machine;

public interface MachineService {

	abstract List<Machine> getMachines();

	void create(Machine machine);

}