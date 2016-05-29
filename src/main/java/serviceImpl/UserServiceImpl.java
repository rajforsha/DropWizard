package serviceImpl;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import couchbase.CouchbaseWrapper;
import domain.User;
import service.UserService;

/**
 * @author shashi
 *
 */
public class UserServiceImpl implements UserService {

	public Boolean createUser(User user) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
		boolean isCreated = CouchbaseWrapper.createUser(user.getName(), result);
		return isCreated;
	}

	public User getUsers(String id) throws JsonParseException, JsonMappingException, IOException {
		return CouchbaseWrapper.getUser(id);
	}

	public Boolean updateUser(User user) throws JsonProcessingException {
		CouchbaseWrapper.updateUser(user);
		return null;
	}

	public Boolean deleteUser(String id) throws JsonProcessingException {
		CouchbaseWrapper.deleteUser(id);
		return null;
	}

}
