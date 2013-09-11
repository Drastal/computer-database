package computer.database.dao;

import java.util.List;

import com.formation.jee.domain.User;

public interface UserDao {
	
	List<User> getUsers();

	void create(User user);
}