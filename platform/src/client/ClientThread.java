package client;

import constants.ConnectionConstants;
import models.SocketHelper;

import java.io.*;
import java.net.Socket;

public record ClientThread(String clientName) implements Runnable{
    @Override
    public void run() {

        try (SocketHelper socketHelper = new SocketHelper(
                new Socket(ConnectionConstants.HOST, ConnectionConstants.PORT))){
            // Create a thread to read all messages from server.
            Runnable reader = new Reader(socketHelper.getScanner(), clientName);
            new Thread(reader).start();

            // Create a thread to write messages to server.
            Runnable writer = new Writer(socketHelper.getPrintWriter(), clientName);
            new Thread(writer).start();

            // Keep running until the server kills the connection.
            while (socketHelper.isConnected()) {
                Thread.sleep(1000); // 1 sec
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Connection to server closed. Stopping client.");
        }
    }
}


/*
Random notes:
Lots of duplicate code setting up input/outputs. Can I move this to a shared utility class?
Should I have a thread to do the writes and a thread to read and print the messages from the server?
Anyway to remove the "probably busy waiting" warning? A better approach to this loop?
 */