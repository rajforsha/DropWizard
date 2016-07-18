package domain;

import java.util.List;

/**
 * @author shashi
 *
 */
public class RootLookup implements Domain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String parentId;
	public List<String> childIds;

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the childIds
	 */
	public List<String> getChildIds() {
		return childIds;
	}

	/**
	 * @param childIds
	 *            the childIds to set
	 */
	public void setChildIds(List<String> childIds) {
		this.childIds = childIds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RootLookup [parentId=" + parentId + ", childIds=" + childIds + "]";
	}

}
