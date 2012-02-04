package com.osbcp.squicss;

public final class PropertyValue {

	private String property;
	private String value;

	public PropertyValue(final String property, final String value) {
		this.property = property;
		this.value = value;
	}

	@Override
	public String toString() {
		return property + ": " + value;
	}

	@Override
	public boolean equals(final Object object) {

		if (object instanceof PropertyValue) {

			PropertyValue target = (PropertyValue) object;

			return target.property.equalsIgnoreCase(property) && target.value.equalsIgnoreCase(value);

		}

		return false;

	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

}
