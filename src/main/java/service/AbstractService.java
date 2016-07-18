package service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import domain.User;

/**
 * @author shashi
 *
 */
public abstract class AbstractService {

	public abstract void afterCreate(User user) throws JsonParseException, JsonMappingException, IOException;

	public abstract void afterDelete(String id) throws JsonParseException, JsonMappingException, IOException;

}
