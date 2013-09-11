package computer.database.service.manager;

import computer.database.service.MachineService;
import computer.database.service.impl.MachineServiceImpl;

public enum ServiceManager {
	
	INSTANCE;
	
	private MachineService machineService;
	
	private ServiceManager() {
		machineService = new MachineServiceImpl();
	}
	
	public MachineService getMachineService() {
		return machineService;
	}
	
}
