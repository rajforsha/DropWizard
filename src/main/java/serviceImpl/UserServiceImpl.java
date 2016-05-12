package serviceImpl;

import domain.User;
import dto.UserDto;
import service.UserService;
import utils.Beanutil;

/**
 * @author shashi
 *
 */
public class UserServiceImpl implements UserService {

	public UserDto getAllUsers() {
		UserDto userDto = new UserDto();
		userDto.setAge(23);
		userDto.setDesignation("GSE");
		userDto.setName("Shashi");
		userDto.setPassword("password");
		User user = new User();
		Beanutil.convert(userDto, user);
		System.out.println(user.toString());
		return userDto;
	}

	public boolean updateDesignation(String designation) {
		return true;
	}

	public User createUser(User user) {
		return user;
	}

	public User createUser(UserDto user) {
		// TODO Auto-generated method stub
		return null;
	}

}
