package models;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SocketHelper extends Socket {

    private final Scanner scanner;
    private final PrintWriter printWriter;

    public SocketHelper(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
        printWriter = new PrintWriter(
                new OutputStreamWriter(outputStream, StandardCharsets.UTF_8),
                true);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }
}
