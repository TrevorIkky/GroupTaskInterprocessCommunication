package com.ds.icde.client;

import java.io.Serializable;

/**
 * The client object a plain old java object
 * It implements serializable to allow the ObjectOutputStreamWriter to send the object to the server
 * */
public class ClientObject implements Serializable {
    public String field;
    public int count;

    public ClientObject(String field, int count) {
        this.field = field;
        this.count = count;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
