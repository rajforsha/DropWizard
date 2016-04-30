import java.util.List;

import annotations.service;

/**
 * 
 */

/**
 * @author shashi
 *
 */
@service
public interface UserService {

	List<User> getAllUsers();

	boolean updateDesignation(String designation);

}
