package resource;

import com.couchbase.client.CouchbaseClient;

import configuration.CouchbaseConfiguration;

/**
 * @author vagrant
 *
 */
public class CouchbaseResource {

	private static CouchbaseClient client;
	public CouchbaseConfiguration config;

	public CouchbaseResource() {
		super();
	}

	public CouchbaseResource(CouchbaseConfiguration config) {
		super();
		this.config = config;
	}

	public static CouchbaseClient getClient() {
		if (client == null) {
			client = couchbase.CouchbaseConnection.getClient(new CouchbaseResource().getConfig());
		}
		return client;
	}

	public static void setClient(CouchbaseClient client) {
		CouchbaseResource.client = client;
	}

	public CouchbaseConfiguration getConfig() {
		return config;
	}

	public void setConfig(CouchbaseConfiguration config) {
		this.config = config;
	}

}
