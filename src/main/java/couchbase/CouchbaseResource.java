package couchbase;

import javax.ws.rs.Path;

import com.wordnik.swagger.annotations.Api;

import configuration.CouchbaseConfiguration;

/**
 * @author vagrant
 *
 */
@Path("/couchbase")
@Api(value = "/users", description = "users operations")
public class CouchbaseResource {

	public CouchbaseConfiguration config;

	public CouchbaseResource() {
		super();
	}

	public CouchbaseResource(CouchbaseConfiguration config) {
		super();
		this.config = config;
		CouchbaseConnection.createConnection(config);
	}

	/**
	 * @return the config
	 */
	public CouchbaseConfiguration getConfig() {
		return config;
	}

	/**
	 * @param config
	 *            the config to set
	 */
	public void setConfig(CouchbaseConfiguration config) {
		this.config = config;
	}

}
