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
