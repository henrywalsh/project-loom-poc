package client;

import constants.ConnectionConstants;

public class Client {

    public static void main(String[] args) {
        for (int i = 0; i < ConnectionConstants.CLIENTS; i++) {
            ClientThread clientThread = new ClientThread("Client" + i);
            new Thread(clientThread).start();
        }
    }

}
