package serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import couchbase.CouchbaseWrapper;
import domain.RootLookup;
import domain.User;
import service.AbstractService;
import service.UserService;

/**
 * @author shashi
 *
 */
public class UserServiceImpl extends AbstractService implements UserService {

	public static ObjectMapper mapper;
	public static final String ROOT_ID = "lookuptable:user:rootid";

	public UserServiceImpl() {
		super();
		mapper = new ObjectMapper();
	}

	public Boolean createUser(User user) throws IOException {
		String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
		afterCreate(user);
		return CouchbaseWrapper.createDocument(user.getName(), result);
	}

	public List<User> getUsers(String id) throws JsonParseException, JsonMappingException, IOException {
		List<User> userList = new ArrayList<User>();
		if (id == null) {
			RootLookup lookup = mapper.readValue(CouchbaseWrapper.getDocument(ROOT_ID).toString(), RootLookup.class);
			List<String> keys = lookup.getChildIds();
			for (String key : keys) {
				userList.add(mapper.readValue(CouchbaseWrapper.getDocument(key).toString(), User.class));
			}
		} else {
			userList.add(mapper.readValue(CouchbaseWrapper.getDocument(id).toString(), User.class));
		}
		return userList;
	}

	public Boolean updateUser(User user)
			throws IllegalArgumentException, JsonParseException, JsonMappingException, IOException {
		User oldUser = mapper.readValue(CouchbaseWrapper.getDocument(user.getName()).toString(), User.class);
		if (user.getAge() != 0)
			oldUser.setAge(user.getAge());
		if (user.getDesignation() != null)
			oldUser.setDesignation(user.getDesignation());
		if (user.getGender() != null)
			oldUser.setGender(user.getGender());
		if (user.getPassword() != null)
			oldUser.setPassword(user.getPassword());
		return CouchbaseWrapper.updateDocument(oldUser.getName(),
				mapper.writerWithDefaultPrettyPrinter().writeValueAsString(oldUser));
	}

	public Boolean deleteUser(String id) throws IOException {
		afterDelete(id);
		return CouchbaseWrapper.deleteDocument(id);
	}

	@Override
	public void afterCreate(User user) throws JsonParseException, JsonMappingException, IOException {
		if (CouchbaseWrapper.getDocument(ROOT_ID) != null) {
			RootLookup lookup = mapper.readValue(CouchbaseWrapper.getDocument(ROOT_ID).toString(), RootLookup.class);
			List<String> childIds = lookup.getChildIds();
			childIds.add(user.getName());
			lookup.setChildIds(childIds);
			CouchbaseWrapper.updateDocument(ROOT_ID,
					mapper.writerWithDefaultPrettyPrinter().writeValueAsString(lookup));

		} else {
			RootLookup lookup = new RootLookup();
			lookup.setParentId(ROOT_ID);
			lookup.setChildIds(Arrays.asList(user.getName()));
			CouchbaseWrapper.createDocument(ROOT_ID,
					mapper.writerWithDefaultPrettyPrinter().writeValueAsString(lookup));
		}
	}

	@Override
	public void afterDelete(String id) throws JsonParseException, JsonMappingException, IOException {
		RootLookup lookup = mapper.readValue(CouchbaseWrapper.getDocument(ROOT_ID).toString(), RootLookup.class);
		List<String> childIds = lookup.getChildIds();
		childIds.remove(id);
		lookup.setChildIds(childIds);
		CouchbaseWrapper.updateDocument(ROOT_ID, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(lookup));
	}

}
