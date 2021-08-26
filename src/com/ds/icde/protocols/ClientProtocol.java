package com.ds.icde.protocols;

import com.ds.icde.server.ResponseObject;

import java.util.ArrayList;
import java.util.StringJoiner;

public class ClientProtocol {
    private final ArrayList<String> responses = new ArrayList<>();

    public ClientProtocol() {
    }

    public void processResponseMessage(ResponseObject response) {
        if (response.code == 200)
            responses.add(response.message);
    }

    public int getResponsesLength() {
        return responses.size();
    }

    public String getAllDetails() {
        StringJoiner stringJoiner = new StringJoiner(">");
        for (String field : responses) {
            stringJoiner.add(field);
        }
        return stringJoiner.toString();
    }
}
