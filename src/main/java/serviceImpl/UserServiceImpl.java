package serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import couchbase.CouchbaseWrapper;
import domain.Domain;
import domain.RootLookup;
import domain.User;
import service.UserService;

/**
 * @author shashi
 *
 */
public class UserServiceImpl implements UserService {

	public static ObjectMapper mapper;
	public static final String ROOT_ID = "lookuptable:user:rootid";

	public UserServiceImpl() {
		super();
		mapper = new ObjectMapper();
	}

	public List<Domain> getUsers(String id) throws JsonParseException, JsonMappingException, IOException {
		List<Domain> userList = new ArrayList<Domain>();
		if (id == null) {
			userList.addAll(findAll());
		} else {
			userList.add((User) findOne(id, User.class));
		}
		return userList;
	}

	public List<Domain> findAll() throws JsonParseException, JsonMappingException, IOException {
		List<Domain> userList = new ArrayList<Domain>();
		RootLookup lookup = mapper.readValue(CouchbaseWrapper.getDocument(ROOT_ID).toString(), RootLookup.class);
		List<String> keys = lookup.getChildIds();
		for (String key : keys) {
			userList.add((User) findOne(key, User.class));
		}
		return userList;
	}

	public Boolean create(Domain entity, Class<?> cls) throws IOException {
		String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entity);
		afterCreate(entity);
		return CouchbaseWrapper.createDocument(((User) entity).getName(), result);

	}

	public Domain findOne(String id, Class<?> cls) throws JsonParseException, JsonMappingException, IOException {
		User user = (User) (mapper.readValue(CouchbaseWrapper.getDocument(id).toString(), cls));
		return user;
	}

	public void update(Domain entity, Class<?> cls) throws JsonParseException, JsonMappingException, IOException {
		User oldUser = (User) mapper.readValue(CouchbaseWrapper.getDocument(((User) entity).getName()).toString(), cls);
		if (((User) entity).getAge() != 0)
			oldUser.setAge(((User) entity).getAge());
		if (((User) entity).getDesignation() != null)
			oldUser.setDesignation(((User) entity).getDesignation());
		if (((User) entity).getGender() != null)
			oldUser.setGender(((User) entity).getGender());
		if (((User) entity).getPassword() != null)
			oldUser.setPassword(((User) entity).getPassword());
		CouchbaseWrapper.updateDocument(oldUser.getName(),
				mapper.writerWithDefaultPrettyPrinter().writeValueAsString(oldUser));

	}

	public void delete(String id) throws JsonParseException, JsonMappingException, IOException {
		afterDelete(id);
		CouchbaseWrapper.deleteDocument(id);
	}

	public void afterCreate(Domain user) throws JsonParseException, JsonMappingException, IOException {
		if (CouchbaseWrapper.getDocument(ROOT_ID) != null) {
			RootLookup lookup = mapper.readValue(CouchbaseWrapper.getDocument(ROOT_ID).toString(), RootLookup.class);
			List<String> childIds = lookup.getChildIds();
			childIds.add(((User) user).getName());
			lookup.setChildIds(childIds);
			CouchbaseWrapper.updateDocument(ROOT_ID,
					mapper.writerWithDefaultPrettyPrinter().writeValueAsString(lookup));

		} else {
			RootLookup lookup = new RootLookup();
			lookup.setParentId(ROOT_ID);
			lookup.setChildIds(Arrays.asList(((User) user).getName()));
			CouchbaseWrapper.createDocument(ROOT_ID,
					mapper.writerWithDefaultPrettyPrinter().writeValueAsString(lookup));
		}
	}

	public void afterDelete(String id) throws JsonParseException, JsonMappingException, IOException {
		RootLookup lookup = mapper.readValue(CouchbaseWrapper.getDocument(ROOT_ID).toString(), RootLookup.class);
		List<String> childIds = lookup.getChildIds();
		childIds.remove(id);
		lookup.setChildIds(childIds);
		CouchbaseWrapper.updateDocument(ROOT_ID, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(lookup));
	}

}
