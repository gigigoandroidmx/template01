package com.gigigo.kretrofitmanager;

/**
 *
 *
 * @author Juan Godínez Vera - 5/10/2017
 * @version 2.0.0
 * @since 2.0.0
 */
public class ResponseState
        extends Throwable {

    private int code;

    public ResponseState(String message) {
        super(message);
    }

    public ResponseState(String message, int code) {
        this(message);
        this.setCode(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getEntryMessage() {
        return String.format("%1$d %2$s", getCode(), getMessage());
    }
}
