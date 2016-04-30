package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import domain.User;
import service.UserService;

/**
 * @author shashi
 *
 */
public class UserServiceImpl implements UserService {

	public List<User> getAllUsers() {

		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setAge(23);
		user1.setDesignation("GSE");
		user1.setName("Shashi");
		user1.setPassword("password");
		userList.add(user1);
		return userList;
	}

	public boolean updateDesignation(String designation) {

		List<User> oldList = getAllUsers();
		User user1 = oldList.get(0);
		user1.setDesignation(designation);
		return true;
	}

	public User createUser(User user) {
		return user;
	}

}
