package server;

import helpers.SocketHelper;

import java.io.*;
import java.util.Scanner;

public class ConnectionHandler implements Runnable {

    private final SocketHelper socketHelper;
    private final Scanner scanner;
    private final PrintWriter printWriter;

    private Thread currentThread;

    public ConnectionHandler(SocketHelper socketHelper) {
        this.socketHelper = socketHelper;
        this.scanner = socketHelper.getScanner();
        this.printWriter = socketHelper.getPrintWriter();
    }

    @Override
    public void run() {
        this.currentThread = Thread.currentThread();
        printWriter.println("Connected to server as thread: " + this.currentThread.getName());

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            printWriter.println("Echo from server: " + line);

            if (line.toLowerCase().trim().equals("exit")) {
                printWriter.println("Closing connection.");

                try {
                    closeConnection();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void closeConnection() throws IOException {
        scanner.close();
        printWriter.close();
        socketHelper.close();
        currentThread.interrupt();
    }
}
