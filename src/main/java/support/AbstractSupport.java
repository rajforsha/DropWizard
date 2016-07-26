package support;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import domain.Domain;
import dto.Dto;

/**
 * @author shashi
 *
 */
public abstract class AbstractSupport<E extends Domain, D extends Dto> {

	public Class<?> domainType;
	public Class<?> dtoType;

	@SuppressWarnings("unchecked")
	public Class<?> getDomainType() {
		return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	public Class<?> getDtoType() {
		return (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

	public abstract GenericService getGenericService();

	public void create(Domain entity) throws JsonProcessingException, IOException {
		getGenericService().create(entity, getDomainType());
	}

	public void update(Domain entity) throws JsonProcessingException, IOException {
		getGenericService().update(entity, getDomainType());
	}

	public void delete(String id) throws JsonParseException, JsonMappingException, IOException {
		getGenericService().delete(id);
	}

}
