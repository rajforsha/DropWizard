
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class MainApplication extends Application<MyConfiguration> {

	public static void main(String args[]) throws Exception {
		new MainApplication().run(args);
	}

	@Override
	public void run(MyConfiguration configuration, Environment environment) throws Exception {
		final UserResource resource = new UserResource() {
		};
		environment.jersey().register(resource);
	}

}
