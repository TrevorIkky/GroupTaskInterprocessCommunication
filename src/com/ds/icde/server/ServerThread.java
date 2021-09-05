package com.ds.icde.server;

import com.ds.icde.client.ClientObject;
import com.ds.icde.protocols.ServerProtocol;

import java.io.*;
import java.net.Socket;

/*
* ServerThread implementing a server protocol to process client objects
* The client object is created from reading an object from the ObjectInputStream
* The ServerProtocol processes the client object and returns a ResponseObject
* containing the message and the code of whether the input was accepted by the server
* The ObjectSocketOutput stream writes the object back to the client.
* If the client has stored >= 7 successful response counts from the client then it closes
* the socket connection.
* */
public class ServerThread extends Thread {

    Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(ObjectOutputStream socketOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
                ObjectInputStream socketInputStream = new ObjectInputStream(this.socket.getInputStream())) {

            ServerProtocol serverProtocol = new ServerProtocol();
            ClientObject clientObject = (ClientObject) socketInputStream.readObject();

            final ResponseObject responseMessage = serverProtocol.processClientInput(clientObject);
            socketOutputStream.writeObject(responseMessage);

            if (clientObject.count >= 7){
                System.out.println("Finished from server...");
                this.socket.close();
            }

        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
