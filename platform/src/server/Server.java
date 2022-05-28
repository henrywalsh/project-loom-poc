package server;

import models.StringTransferSocket;

import java.io.*;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) {
        connectToServer();
    }

    public static void connectToServer() {

        try (ServerSocket serverSocket = new ServerSocket(420)) {
            for (int i = 0; i < 4; i++) {
                StringTransferSocket socket = new StringTransferSocket(serverSocket.accept());

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
