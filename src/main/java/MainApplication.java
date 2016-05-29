import configuration.MyConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import resource.CouchbaseResource;
import resource.UserResource;

public class MainApplication extends Application<MyConfiguration> {

	public static void main(String args[]) throws Exception {
		new MainApplication().run(args);
	}

	@Override
	public void run(MyConfiguration configuration, Environment environment) throws Exception {
		environment.jersey().register(new UserResource());
		environment.jersey().register(new CouchbaseResource(configuration.getConfig()));
		System.out.println(configuration.getConfig().toString());
		/*
		 * environment.jersey().register(new AuthDynamicFeature(new
		 * BasicCredentialAuthFilter.Builder<User>() .setAuthenticator(new
		 * SimpleAuthenticator()).setRealm(getName()).buildAuthFilter()));
		 */
	}

	@Override
	public void initialize(Bootstrap<MyConfiguration> bootstrap) {

		bootstrap.addBundle(new SwaggerBundle<MyConfiguration>() {
			@Override
			protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(MyConfiguration configuration) {
				return configuration.swaggerBundleConfiguration;
			}
		});
	}

}
