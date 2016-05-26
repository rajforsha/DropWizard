package serviceImpl;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import couchbase.CouchbaseWrapper;
import domain.CouchbaseConfiguration;
import domain.User;
import service.UserService;

/**
 * @author shashi
 *
 */
public class UserServiceImpl implements UserService {

	public static CouchbaseWrapper wrapper = null;

	public boolean updateDesignation(String designation) {
		return true;
	}

	public Boolean createUser(User user) throws JsonProcessingException {
		CouchbaseConfiguration config = new CouchbaseConfiguration();
		config.setBucket("default");
		config.setNode("http://127.0.0.1:8091/pools");
		config.setPassword("123456");
		wrapper = new CouchbaseWrapper(config);
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
		boolean isCreated = wrapper.createUser(user.getName(), result);
		return isCreated;
	}

	public User getUsers(String id) throws JsonParseException, JsonMappingException, IOException {
		CouchbaseConfiguration config = new CouchbaseConfiguration();
		config.setBucket("default");
		config.setNode("http://127.0.0.1:8091/pools");
		config.setPassword("123456");
		wrapper = new CouchbaseWrapper(config);
		return wrapper.getUser(id);
	}

}
