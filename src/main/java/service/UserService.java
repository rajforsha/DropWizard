package service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import annotations.service;
import configuration.CouchbaseConfiguration;
import domain.User;

/**
 * @author shashi
 *
 */
@service
public interface UserService {

	User getUsers(String id, CouchbaseConfiguration config)
			throws JsonParseException, JsonMappingException, IOException;

	boolean updateDesignation(String designation);

	Boolean createUser(User user) throws JsonProcessingException;

}
