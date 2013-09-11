package computer.database.service;

import java.util.List;

import com.formation.jee.domain.User;

public interface UserService {

	abstract List<User> getUsers();

	void create(User user);

}