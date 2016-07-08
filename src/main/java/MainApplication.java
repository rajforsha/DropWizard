import configuration.CouchbaseConfiguration;
import configuration.MyConfiguration;
import couchbase.CouchbaseConnection;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import resource.UserResource;

public class MainApplication extends Application<MyConfiguration> {

	private CouchbaseConfiguration couchbaseConfiguration;

	public static void main(String args[]) throws Exception {
		new MainApplication().run(args);
	}

	@Override
	public void run(MyConfiguration configuration, Environment environment) throws Exception {
		environment.jersey().register(new UserResource());
		setCouchbaseConfiguration(configuration.getConfig());
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

	public CouchbaseConfiguration getCouchbaseConfiguration() {
		return couchbaseConfiguration;
	}

	public void setCouchbaseConfiguration(CouchbaseConfiguration couchbaseConfiguration) {
		this.couchbaseConfiguration = couchbaseConfiguration;
		CouchbaseConnection.createConnection(couchbaseConfiguration);
	}

}
