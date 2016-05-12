package resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import domain.User;
import dto.UserDto;
import service.UserService;
import serviceImpl.UserServiceImpl;

/**
 * @author shashi
 *
 */
@Path("/getHello")
@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
public class UserResource {

	private UserService userService = new UserServiceImpl();
	/*
	 * @GET public String getHello() { return "hello World"; }
	 */

	@GET
	public UserDto getUsers() {
		return userService.getAllUsers();
	}

	@PUT
	public boolean updateDesigantion(@QueryParam("designation") String designation) {
		return userService.updateDesignation(designation);
	}

	@POST
	public User createUser(UserDto user) {
		return (userService.createUser(user));
	}
}
