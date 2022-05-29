package client;

import java.util.Scanner;

public record Reader(Scanner scanner, String clientName) implements Runnable {

    @Override
    public void run() {
        while (scanner.hasNextLine()) {
            System.out.println(clientName + ", " + scanner.nextLine());
        }
    }
}
