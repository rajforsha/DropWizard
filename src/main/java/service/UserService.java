package service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import annotations.service;
import domain.User;

/**
 * @author shashi
 *
 */
@service
public interface UserService {

	User getUsers(String id) throws JsonParseException, JsonMappingException, IOException;

	Boolean updateUser(User user) throws JsonProcessingException;

	Boolean createUser(User user) throws JsonProcessingException;

	Boolean deleteUser(String id) throws JsonProcessingException;

}
