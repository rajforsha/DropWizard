package couchbase;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.User;
import resource.CouchbaseResource;

/**
 * @author shashi
 *
 */
public class CouchbaseWrapper {
	public static ObjectMapper mapper = new ObjectMapper();

	public static boolean createUser(String id, String result) {

		if (resource.CouchbaseResource.getClient().add(id, result) != null) {
			return true;
		}
		return false;
	}

	public static User getUser(String id) throws JsonParseException, JsonMappingException, IOException {

		Object result = CouchbaseResource.getClient().get(id);

		User user = mapper.readValue(String.valueOf(result), User.class);
		return user;
	}

	public static void updateUser(User user) throws JsonProcessingException {
		CouchbaseResource.getClient().replace(user.getName(),
				mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user));

	}

	public static void deleteUser(String id) throws JsonProcessingException {
		CouchbaseResource.getClient().delete(id);
	}
}
