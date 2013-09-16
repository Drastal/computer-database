package computer.database.service.impl;

import java.util.List;

import computer.database.dao.CompanyDao;
import computer.database.dao.MachineDao;
import computer.database.dao.manager.DaoManager;
import computer.database.domain.Company;
import computer.database.domain.Machine;
import computer.database.service.DatabaseService;

/*
 * Classe DatabaseServiceImpl
 * Coeur de la couche service, accès aux DAO de machine et company
 */

public class DatabaseServiceImpl implements DatabaseService {

	MachineDao machineDao;
	CompanyDao companyDao;
	
	public DatabaseServiceImpl() {
		machineDao = DaoManager.INSTANCE.getMachineDao();
		companyDao = DaoManager.INSTANCE.getCompanyDao();
	}
	
	@Override
	public List<Machine> getMachines(String searching) {
		return machineDao.getMachines(searching);
	}

	@Override
	public List<Machine> getMachines(String searching, int resultPerPage, int pageNumber) {
		return machineDao.getMachines(searching, resultPerPage, pageNumber);
	}	
	
	@Override
	public Machine getMachine(long id) {
		return machineDao.getMachine(id);
	}
	
	@Override
	public List<Company> getCompanies() {
		return companyDao.getCompanies();
	}
	
	public Company getCompany(long id){
		return companyDao.getCompany(id);
	}
	
	@Override
	public void create(Machine machine) {
		machineDao.create(machine);
	}
}
