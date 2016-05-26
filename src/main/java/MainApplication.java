import domain.MyConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import resource.UserResource;

public class MainApplication extends Application<MyConfiguration> {

	public static void main(String args[]) throws Exception {
		new MainApplication().run(args);
	}

	@Override
	public String getName() {
		return "shashi";
	}

	@Override
	public void run(MyConfiguration configuration, Environment environment) throws Exception {
		final UserResource resource = new UserResource() {
		};
		environment.jersey().register(resource);

		/*
		 * environment.jersey().register(new AuthDynamicFeature(new
		 * BasicCredentialAuthFilter.Builder<User>() .setAuthenticator(new
		 * SimpleAuthenticator()).setRealm(getName()).buildAuthFilter()));
		 */
	}

}
