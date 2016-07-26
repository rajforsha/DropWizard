package support;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import domain.Domain;

/**
 * @author shashi
 *
 */
public interface GenericService {

	public Boolean create(Domain entity, Class<?> cls) throws JsonProcessingException, IOException;

	public Domain findOne(String id, Class<?> cls) throws JsonParseException, JsonMappingException, IOException;

	public List<Domain> findAll() throws JsonParseException, JsonMappingException, IOException;

	public void update(Domain enity, Class<?> cls) throws JsonParseException, JsonMappingException, IOException;

	public void delete(String id) throws JsonParseException, JsonMappingException, IOException;

	public abstract void afterCreate(Domain user) throws JsonParseException, JsonMappingException, IOException;

	public abstract void afterDelete(String id) throws JsonParseException, JsonMappingException, IOException;

}
