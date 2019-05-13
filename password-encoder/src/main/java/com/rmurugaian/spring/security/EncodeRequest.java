package com.rmurugaian.spring.security;

/**
 * @author rmurugaian 2019-05-13
 */
public class EncodeRequest {

    private String value;
    private String encodeType;

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getEncodeType() {
        return encodeType;
    }

    public void setEncodeType(final String encodeType) {
        this.encodeType = encodeType;
    }
}
