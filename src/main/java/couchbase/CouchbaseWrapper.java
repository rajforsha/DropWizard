package couchbase;

import java.io.IOException;

import com.couchbase.client.CouchbaseClient;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.CouchbaseConfiguration;
import domain.User;

/**
 * @author shashi
 *
 */
public class CouchbaseWrapper {

	public static CouchbaseClient client = null;
	public CouchbaseConfiguration config;

	public CouchbaseWrapper(CouchbaseConfiguration config) {
		super();
		this.config = config;
	}

	public CouchbaseWrapper() {
		super();
	}

	public boolean createUser(String id, String result) {
		if (client == null) {
			client = CouchbaseResource.getClient(config);
		}
		if (client.add(id, result) != null) {
			return true;
		}
		return false;
	}

	public User getUser(String id) throws JsonParseException, JsonMappingException, IOException {
		if (client == null) {
			client = CouchbaseResource.getClient(config);
		}
		Object result = client.get(id);
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(String.valueOf(result), User.class);
		return user;
	}
}
