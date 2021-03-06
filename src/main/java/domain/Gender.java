package domain;

/**
 * @author shashi
 *
 */
public enum Gender implements EnumMatcher {
	MALE("male"), FEMALE("female");
	private String value;

	Gender(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
