package client;

import models.StringTransferSocket;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        try (StringTransferSocket socket = new StringTransferSocket(new Socket("127.0.0.1", 420))){
            // Create a thread to read all messages from server.
            Runnable readHandler = new ReadHandler(socket.getScanner());
            new Thread(readHandler).start();

            // Create a thread to write messages to server.
            Runnable writeHandler = new WriteHandler(socket.getPrintWriter());
            new Thread(writeHandler).start();

            // Keep running until the server kills the connection.
            while (socket.isConnected()) {
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