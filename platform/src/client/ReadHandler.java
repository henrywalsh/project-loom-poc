package client;

import java.util.Scanner;

public record ReadHandler(Scanner scanner) implements Runnable {

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();

        while (scanner.hasNextLine()) {
            System.out.println(currentThread.getName() + ", " + scanner.nextLine());
        }
    }
}
