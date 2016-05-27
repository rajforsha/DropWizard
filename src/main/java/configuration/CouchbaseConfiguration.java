package configuration;

import java.util.List;

/**
 * @author shashi
 *
 */
public class CouchbaseConfiguration {

	private List<String> node;
	private String bucket;
	private String password;

	/**
	 * @return the node
	 */
	public List<String> getNode() {
		return node;
	}

	/**
	 * @param node
	 *            the node to set
	 */
	public void setNode(List<String> node) {
		this.node = node;
	}

	/**
	 * @return the bucket
	 */
	public String getBucket() {
		return bucket;
	}

	/**
	 * @param bucket
	 *            the bucket to set
	 */
	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CouchbaseConfiguration [node=" + node + ", bucket=" + bucket + ", password=" + password + "]";
	}
}
