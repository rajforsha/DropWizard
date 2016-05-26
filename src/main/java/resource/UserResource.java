package resource;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import domain.User;
import service.UserService;
import serviceImpl.UserServiceImpl;

/**
 * @author shashi
 *
 */
@Path("/getUsers")
@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
public class UserResource {

	private UserService userService = new UserServiceImpl();
	/*
	 * @GET public String getHello() { return "hello World"; }
	 */

	@GET
	public Response getUsers(@QueryParam(value = "name") String name)
			throws JsonParseException, JsonMappingException, IOException {
		return Response.ok(userService.getUsers(name)).status(200).build();
	}

	@PUT
	public boolean updateDesigantion(@QueryParam("designation") String designation) {
		return userService.updateDesignation(designation);
	}

	@POST
	public Response createUser(User user) throws JsonProcessingException {
		Boolean isCreated = userService.createUser(user);
		System.out.println("created::" + isCreated);
		return Response.ok().status(200).build();
	}
}
