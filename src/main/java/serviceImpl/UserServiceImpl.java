package serviceImpl;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import configuration.CouchbaseConfiguration;
import configuration.MyConfiguration;
import couchbase.CouchbaseWrapper;
import domain.User;
import service.UserService;

/**
 * @author shashi
 *
 */
public class UserServiceImpl implements UserService {

	public static CouchbaseWrapper wrapper = null;
	public MyConfiguration configuration;

	public boolean updateDesignation(String designation) {
		return true;
	}

	public Boolean createUser(User user) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
		boolean isCreated = wrapper.createUser(user.getName(), result);
		return isCreated;
	}

	public User getUsers(String id, CouchbaseConfiguration config)
			throws JsonParseException, JsonMappingException, IOException {

		return wrapper.getUser(id);
	}

}
