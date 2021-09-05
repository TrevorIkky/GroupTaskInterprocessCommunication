package com.ds.icde.server;

import com.ds.icde.Config;
import com.ds.icde.interfaces.BaseInterface;

import java.io.IOException;
import java.net.ServerSocket;

/*
* Socket server continuously spawns a thread waiting for client connections
* The thread closes when the connection by the client is ended. A new thread is
* created waiting for a client to connect to it again.
* */

public class SocketServer implements BaseInterface {

    private static ServerSocket serverSocket;

    public static void main(String[] args) throws  IOException {
        serverSocket = new ServerSocket(Config.DEFAULT_PORT);

        /**
         * The server.accept() method is blocking. The loop will not
         * start a new server thread until the socket receives a connection
         * **/
        String START_MSG = String.format("Server thread started, running @localhost:%s ...", Config.DEFAULT_PORT);

        while (true){
            System.out.println(START_MSG);
            ServerThread serverThread = new ServerThread(serverSocket.accept());
            serverThread.start();
        }
    }

    @Override
    public void stop() {
        if (!serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }
    }
}
