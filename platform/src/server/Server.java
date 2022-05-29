package server;

import constants.ConnectionConstants;
import helpers.SocketHelper;

import java.io.*;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) {
        connectToServer();
    }

    public static void connectToServer() {

        try (ServerSocket serverSocket = new ServerSocket(ConnectionConstants.PORT)) {
            while (true) {
                SocketHelper socket = new SocketHelper(serverSocket.accept());

                Runnable connectionHandler = new ConnectionHandler(socket);

                new Thread(connectionHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

/*
Notes:
Can I maintain state with open connections with a cap of connections?
 */
