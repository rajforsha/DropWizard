package couchbase;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.User;

/**
 * @author shashi
 *
 */
public class CouchbaseWrapper {
	public static ObjectMapper mapper = new ObjectMapper();

	public static boolean createUser(String id, String result) {

		System.out.println(CouchbaseConnection.getClient().toString());
		return CouchbaseConnection.getClient().add(id, result) != null;
	}

	public static User getUser(String id) throws JsonParseException, JsonMappingException, IOException {
		System.out.println(CouchbaseConnection.getClient().toString());
		Object result = CouchbaseConnection.getClient().get(id);
		User user = mapper.readValue(result.toString(), User.class);
		return user;
	}

	public static void updateUser(User user) throws JsonProcessingException {
		CouchbaseConnection.getClient().replace(user.getName(),
				mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user));

	}

	public static void deleteUser(String id) throws JsonProcessingException {
		CouchbaseConnection.getClient().delete(id);
	}
}
