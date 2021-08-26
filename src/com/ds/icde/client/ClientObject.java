package com.ds.icde.client;

import java.io.Serializable;

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
