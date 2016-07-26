package resource;

import java.io.IOException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import domain.OnCreateValidate;
import domain.OnUpdateValidate;
import domain.User;
import dto.UserDto;
import io.dropwizard.validation.Validated;
import service.UserService;
import support.AbstractSupport;
import support.GenericService;

/**
 * @author shashi
 *
 */

@Path("/users")
@Api(value = "/users", description = "users operations")
@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
@Singleton
public class UserResource extends AbstractSupport<User, UserDto> {

	private UserService userService;

	@Inject
	public UserResource(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get users by id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "server error") })
	public Response getUsers(@QueryParam(value = "name") String name)
			throws JsonParseException, JsonMappingException, IOException {
		return Response.ok(userService.getUsers(name)).status(200).build();
	}

	@POST
	@ApiOperation(value = "create users")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "server error") })

	public Response createUser(@ApiParam @Validated(value = OnCreateValidate.class) User user) throws IOException {
		super.create(user);
		return Response.ok().status(200).build();
	}

	@PUT
	@ApiOperation(value = "update users by id")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "server error") })
	public Response updateDesigantion(@ApiParam @Validated(value = OnUpdateValidate.class) User user)
			throws IllegalArgumentException, IOException {
		super.update(user);
		return Response.ok().status(200).build();
	}

	@DELETE
	@ApiOperation(value = "delete users by id")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "server error") })
	public Response deleteUser(@QueryParam(value = "id") String id) throws IOException {
		super.delete(id);
		return Response.ok().status(200).build();
	}

	@Override
	public GenericService getGenericService() {
		return userService;
	}

}
