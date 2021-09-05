package com.ds.icde.protocols;

import com.ds.icde.client.ClientObject;
import com.ds.icde.server.ResponseObject;

import java.util.Arrays;
import java.util.List;

/*
* The ServerProtocol receives a client object with the count of successful
* responses that has been processed by the client,
* The successful responses count allow the protocol to know which field is being processed and
* pick a valid message to send to the client.
* */
public class ServerProtocol {
    public ResponseObject processClientInput(ClientObject input) {
        String message;
        int code = 200;

        switch (input.count) {
            case 0 -> message = String.format("Student number received. Your id is: %s", input.field);
            case 1 -> message = String.format("Name received. Your name is: %s", input.field);
            case 2 -> message = String.format("Faculty received. Your faculty is: %s", input.field);
            case 3 -> message = String.format("Course received. Your course is: %s", input.field);
            case 4 -> message = String.format("Degree received. Your degree is: %s", input.field);
            case 5 -> message = String.format("Personal code is: %s. Hit send to send all details.", input.field);
            case 6 -> {
                List<String> details = Arrays.asList(input.field.split(">"));
                int detailsCount = details.size();
                message = String.format("Server received %s details from client", detailsCount);
            }
            default -> message = "Mmmh, closing this connection now... Got all inputs";
        }
        return new ResponseObject(message, code);
    }


}
