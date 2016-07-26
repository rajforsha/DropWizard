package service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import annotations.service;
import domain.Domain;
import support.GenericService;

/**
 * @author shashi
 *
 */
@service
public interface UserService extends GenericService {

	List<Domain> getUsers(String id) throws JsonParseException, JsonMappingException, IOException;

}
