package com.ds.icde.server;

import java.io.Serializable;

/*
 * The ResponseObject stores the server response and the code to
 * serialize and send to the client.
 * Implements serializable
 *  */

public class ResponseObject implements Serializable {
    public String message;
    public int code;

    public ResponseObject(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
