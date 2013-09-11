package computer.database.service.impl;

import java.util.List;

import computer.database.dao.CompanyDao;
import computer.database.dao.MachineDao;
import computer.database.dao.manager.DaoManager;
import computer.database.domain.Company;
import computer.database.domain.Machine;
import computer.database.service.DatabaseService;

public class DatabaseServiceImpl implements DatabaseService {

	MachineDao machineDao;
	CompanyDao companyDao;
	
	public DatabaseServiceImpl() {
		machineDao = DaoManager.INSTANCE.getMachineDao();
		companyDao = DaoManager.INSTANCE.getCompanyDao();
	}
	/* (non-Javadoc)
	 * @see com.formation.jee.service.impl.UserService#getUsers()
	 */
	@Override
	public List<Machine> getMachines() {
		return machineDao.getMachines();
	}
	
	@Override
	public List<Company> getCompanies() {
		return companyDao.getCompanies();
	}
	
	@Override
	public void create(Machine machine) {
		machineDao.create(machine);
	}
	
}
