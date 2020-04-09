package com.codewindy.mongodb.pojo;


public enum ErrorEnum {
    SUCCESS("000000", "OK"), ERROR("404000", "FAIL");

    private String key;
    private String value;

    private ErrorEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
