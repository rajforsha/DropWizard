package couchbase;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.couchbase.client.CouchbaseClient;

import configuration.CouchbaseConfiguration;

/**
 * @author shashi
 *
 */
public class CouchbaseConnection {

	public static CouchbaseClient client;

	/**
	 * @return the client
	 */
	public static CouchbaseClient getClient() {
		return client;
	}

	public static void createConnection(CouchbaseConfiguration config) {

		List<URI> uriList = new ArrayList<URI>();
		for (String uri : config.getNode()) {
			uriList.add(URI.create(uri));
		}
		try {
			client = new CouchbaseClient(uriList, config.getBucket(), config.getPassword());
			if (client != null) {
				System.out.println("couchbase Connection Done!!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
