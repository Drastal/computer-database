package computer.database.service.manager;

import computer.database.service.DatabaseService;
import computer.database.service.impl.DatabaseServiceImpl;


public enum ServiceManager {
	
	INSTANCE;
	
	private DatabaseService databaseService;
	
	private ServiceManager() {
		databaseService = new DatabaseServiceImpl();
	}
	
	public DatabaseService getMachineService() {
		return databaseService;
	}
	
} 
