package computer.database.service.impl;

import java.util.List;

import computer.database.dao.MachineDao;
import computer.database.dao.manager.DaoManager;
import computer.database.domain.Machine;
import computer.database.service.MachineService;

public class MachineServiceImpl implements MachineService {

	MachineDao machineDao;
	
	public MachineServiceImpl() {
		machineDao = DaoManager.INSTANCE.getMachineDao();
	}
	/* (non-Javadoc)
	 * @see com.formation.jee.service.impl.UserService#getUsers()
	 */
	@Override
	public List<Machine> getMachines() {
		return machineDao.getMachines();
	}
	
	@Override
	public void create(Machine machine) {
		machineDao.create(machine);
	}
	
}
