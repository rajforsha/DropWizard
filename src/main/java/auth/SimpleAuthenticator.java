package auth;

import com.google.common.base.Optional;

import domain.User;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

/**
 * @author shashi
 *
 */
public class SimpleAuthenticator implements Authenticator<BasicCredentials, User> {

	public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {

		if ("secret".equals(credentials.getPassword())) {
			return Optional.of(new User(credentials.getUsername()));
		} else
			return Optional.absent();
	}

}
