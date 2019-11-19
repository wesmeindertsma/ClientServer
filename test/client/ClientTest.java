package client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ClientTest {

    @Test
    void sendMessage() throws IOException {
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);
        String response = client.sendMessage("hello server");
        client.stopConnection();

        Assertions.assertEquals("hello client", response);
    }
}