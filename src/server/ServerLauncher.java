package server;

import client.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerLauncher {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean keepgoing;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port, 1, InetAddress.getByName("192.168.1.135"));
        //serverSocket = new ServerSocket(port);
        keepgoing = true;


        while(keepgoing) {
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String greeting = in.readLine();
            System.out.println(greeting);
            if ("hello server".equals(greeting)) {
                out.println("hello client");
            } else {
                out.println("unrecognised greeting");
            }
        }
        stop();
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        ServerLauncher server = new ServerLauncher();
        System.out.println("Starting server");
        server.start(6666);
    }
}
