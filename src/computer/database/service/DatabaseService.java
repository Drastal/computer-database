package computer.database.service;

import java.util.List;

import computer.database.domain.Company;
import computer.database.domain.Machine;

public interface DatabaseService {

	abstract List<Machine> getMachines();
	
	abstract List<Company> getCompanies();

	void create(Machine machine);

}