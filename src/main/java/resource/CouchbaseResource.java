package resource;

import javax.ws.rs.Path;

import com.couchbase.client.CouchbaseClient;
import com.wordnik.swagger.annotations.Api;

import configuration.CouchbaseConfiguration;
import couchbase.CouchbaseConnection;

/**
 * @author vagrant
 *
 */
@Path("/couchbase")
@Api(value = "couchbaseResource")
public class CouchbaseResource {

	private static CouchbaseClient client;
	public static CouchbaseConfiguration config;

	public CouchbaseResource() {
		super();
	}

	@SuppressWarnings("static-access")
	public CouchbaseResource(CouchbaseConfiguration config) {
		super();
		this.config = config;
	}

	public static CouchbaseClient getClient() {
		if (CouchbaseResource.client == null) {
			client = CouchbaseConnection.getClient(config);
		}
		return client;
	}
}
