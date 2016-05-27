package couchbase;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.User;
import resource.CouchbaseResource;

/**
 * @author shashi
 *
 */
public class CouchbaseWrapper {

	public boolean createUser(String id, String result) {

		if (resource.CouchbaseResource.getClient().add(id, result) != null) {
			return true;
		}
		return false;
	}

	public User getUser(String id) throws JsonParseException, JsonMappingException, IOException {

		Object result = CouchbaseResource.getClient().get(id);
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(String.valueOf(result), User.class);
		return user;
	}
}
