package computer.database.dao.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import computer.database.dao.CompanyDao;
import computer.database.dao.MachineDao;
import computer.database.dao.impl.MachineDaoImpl;

public enum DaoManager {

	INSTANCE;

	private MachineDao machineDao;
	private CompanyDao companyDao;
	private EntityManagerFactory emf;
	
	private DaoManager() {
		emf = Persistence.createEntityManagerFactory("epfPU");
		machineDao = new MachineDaoImpl();
	}

	public MachineDao getMachineDao() {
		return machineDao;
	}
	
	public CompanyDao getCompanyDao() {
		return companyDao;
	}
	
	public EntityManager getEntityManager() {

		return emf.createEntityManager();
	}

}
