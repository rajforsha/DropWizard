package couchbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @author shashi
 *
 */
public class CouchbaseWrapper {

	public static boolean createDocument(String id, String result) {
		return CouchbaseConnection.getClient().add(id, result) != null;
	}

	public static Object getDocument(String id) throws JsonParseException, JsonMappingException, IOException {
		return CouchbaseConnection.getClient().get(id);
	}

	public static Boolean updateDocument(String id, String result) throws JsonProcessingException {

		return CouchbaseConnection.getClient().replace(id, result) != null;
	}

	public static Boolean deleteDocument(String id) throws JsonProcessingException {
		return CouchbaseConnection.getClient().delete(id) != null;
	}

	public static List<Object> findAll(List<String> keys) {
		Map<String, Object> map = CouchbaseConnection.getClient().getBulk(keys);
		List<Object> objectList = new ArrayList<Object>();
		for (Entry<String, Object> object : map.entrySet()) {
			objectList.add(object);
		}
		return objectList;
	}
}
