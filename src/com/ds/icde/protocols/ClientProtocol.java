package com.ds.icde.protocols;

import com.ds.icde.server.ResponseObject;

import java.util.ArrayList;
import java.util.StringJoiner;

/*
 * The ClientProtocol stores all response messages from the server if the response code
 * supplied by the server is 200
 * The @function getResponsesLength stores the number of successful responses from the
 * server.
 * The @function getAllDetails combines all the successful responses of the server, i.e.
 * the all details sent to the client and creates a joined string to send to the server as a
 * final step by the client.
 * */

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
