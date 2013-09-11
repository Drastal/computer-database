package computer.database.dao.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.formation.jee.dao.UserDao;
import com.formation.jee.dao.impl.UserDaoImpl;

public enum DaoManager {

	INSTANCE;

	private UserDao userDao;
	private EntityManagerFactory emf;
	
	private DaoManager() {
		emf = Persistence.createEntityManagerFactory("epfPU");
		userDao = new UserDaoImpl();
	}

	public UserDao getUserDao() {
		return userDao;
	}
	
	public EntityManager getEntityManager() {

		return emf.createEntityManager();
	}

}
