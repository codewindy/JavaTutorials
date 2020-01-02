package com.codewindy.mongodb.pojo;

public enum ErrorEnum {
	SUCCESS(0, "OK"), ERROR(400, "FAIL");

	private int key;
	private String value;

	private ErrorEnum(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
