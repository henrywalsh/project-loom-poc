package client;

import java.io.PrintWriter;

public record WriteHandler(PrintWriter printWriter) implements Runnable {

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        System.out.println(currentThread.getName() + ", Starting to send messages to server:");

        // Send n number of messages to server, with a second gap between each one.
        for (int i = 0; i < 10; i++) {
            System.out.println(currentThread.getName() + ", Sending: " + i);
            printWriter.println(i);

            try {
                Thread.sleep(1000); // 1 sec
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        printWriter.println("exit");
    }
}
