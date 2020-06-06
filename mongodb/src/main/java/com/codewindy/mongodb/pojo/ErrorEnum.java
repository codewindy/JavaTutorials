package com.codewindy.mongodb.pojo;


import lombok.Getter;

@Getter
public enum ErrorEnum {
    SUCCESS("000000", "OK"), ERROR("404000", "FAIL");

    private String key;
    private String value;

    private ErrorEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    //001001 如果设置为Integer 会被转换程八进制的513
}
