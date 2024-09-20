package com.ufril.medtran.enumeration;

public enum FCMNotificationParameter {

	SOUND("default"),
	COLOR("#FFFF00");

	private String value;

	FCMNotificationParameter(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
