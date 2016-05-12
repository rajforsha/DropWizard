package service;

import annotations.service;
import domain.User;
import dto.UserDto;

/**
 * @author shashi
 *
 */
@service
public interface UserService {

	UserDto getAllUsers();

	boolean updateDesignation(String designation);

	User createUser(UserDto user);

}
