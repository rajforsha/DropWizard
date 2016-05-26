package couchbase;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.couchbase.client.CouchbaseClient;

import domain.CouchbaseConfiguration;

/**
 * @author shashi
 *
 */
public class CouchbaseResource {

	public static CouchbaseClient client = null;

	public static CouchbaseClient getClient(CouchbaseConfiguration config) {

		List<URI> uriList = new ArrayList<URI>();
		uriList.add(URI.create(config.getNode()));
		try {
			client = new CouchbaseClient(uriList, config.getBucket(), config.getPassword());
			if (client != null) {
				System.out.println("couchbase Connection Done!!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return client;

	}
}
