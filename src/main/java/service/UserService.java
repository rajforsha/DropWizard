package service;

import java.util.List;

import annotations.service;
import domain.User;

/**
 * @author shashi
 *
 */
@service
public interface UserService {

	List<User> getAllUsers();

	boolean updateDesignation(String designation);

	User createUser(User user);

}
