package client;

import java.io.PrintWriter;

public record Writer(PrintWriter printWriter, String clientName) implements Runnable {

    @Override
    public void run() {
        System.out.println(clientName + ", Starting to send messages to server:");

        // Send n number of messages to server, with a second gap between each one.
        for (int i = 0; i < 10; i++) {
            String message = "Message" + i;
            System.out.println(clientName + ", Sending: " + message);
            printWriter.println(message);

            try {
                Thread.sleep(1000); // 1 sec
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        printWriter.println("exit");
    }
}
