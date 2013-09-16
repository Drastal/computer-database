package computer.database.service;

import java.util.List;

import computer.database.domain.Company;
import computer.database.domain.Machine;

public interface DatabaseService {

	abstract List<Machine> getMachines(String searching);
	abstract List<Machine> getMachines(String searching, int resultPerPage, int pageNumber);
	abstract Machine getMachine(long id);
	
	abstract List<Company> getCompanies();
	abstract Company getCompany(long id);

	void create(Machine machine);
	
}