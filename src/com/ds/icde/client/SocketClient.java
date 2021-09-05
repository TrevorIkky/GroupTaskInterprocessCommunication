package com.ds.icde.client;

import com.ds.icde.protocols.ClientProtocol;
import com.ds.icde.server.ResponseObject;

import java.io.*;
import java.net.Socket;


public class SocketClient {

    private  final String address;
    private final int port;
    public ClientProtocol clientProtocol;


    public SocketClient(String address, int port) throws IOException {
        this.address = address;
        this.port = port;
        this.clientProtocol = new ClientProtocol();
    }

    public ResponseObject sendRequest(ClientObject clientObject) {
        /*
         * Steps:
         * Create a connection to the server using the address & port
         * Get the socket's object output stream to write the client object to be received by the server
         * Write the client object to the socket output stream and open an ObjectInputStream
         * The object input stream waits for the server response implemented as a ResponseObject
         * The client protocol adds the message contained inside the response object to an arraylist
         * This arraylist keeps track of the response messages from the server
         * After the message is added, the connection to the server is closed
         * */
        ResponseObject responseObject;
        try{
            Socket socket = new Socket(address, port);
            ObjectOutputStream socketOutputStream = new ObjectOutputStream(socket.getOutputStream());

            socketOutputStream.writeObject(clientObject);

            ObjectInputStream socketInputStream = new ObjectInputStream(socket.getInputStream());
            responseObject = (ResponseObject) socketInputStream.readObject();

            clientProtocol.processResponseMessage(responseObject);

            socket.close();

        } catch (IOException | ClassNotFoundException e) {
            responseObject = new ResponseObject(e.getMessage(), 500);
        }
        return responseObject;
    }
}
