package com.ds.icde.server;

import com.ds.icde.client.ClientObject;
import com.ds.icde.protocols.ServerProtocol;

import java.io.*;
import java.net.Socket;

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
