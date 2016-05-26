package resource;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import domain.User;
import service.UserService;
import serviceImpl.UserServiceImpl;

/**
 * @author shashi
 *
 */
@Path("/users")
@Api(value = "/users", description = "users operations")
@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
public class UserResource {

	private UserService userService = new UserServiceImpl();
	/*
	 * @GET public String getHello() { return "hello World"; }
	 */

	@GET
	@Path("/getUsers")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "users")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "server error") })
	public Response getUsers(@QueryParam(value = "name") String name)
			throws JsonParseException, JsonMappingException, IOException {
		return Response.ok(userService.getUsers(name)).status(200).build();
	}

	@PUT
	@Path("/updateUsers")
	@ApiOperation(value = "users")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "server error") })
	public boolean updateDesigantion(@QueryParam("designation") String designation) {
		return userService.updateDesignation(designation);
	}

	@POST
	@Path("/createUsers")
	@ApiOperation(value = "users")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "server error") })
	public Response createUser(User user) throws JsonProcessingException {
		Boolean isCreated = userService.createUser(user);
		System.out.println("created::" + isCreated);
		return Response.ok().status(200).build();
	}
}
