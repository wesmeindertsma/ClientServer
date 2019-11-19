package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientLauncher {


    private static Socket socket;

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String sendMessage = "";
        while (!sendMessage.equals("stop" + "\n")) {
            try {
                String host = "localhost";
                int port = 6666;
                InetAddress address = InetAddress.getByName("192.168.1.135");
                socket = new Socket(address, port);
                //Send the message to the server
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);

                String number = "2";

                sendMessage = keyboard.nextLine() + "\n";
                bw.write(sendMessage);
                bw.flush();
                System.out.println("Message sent to the server : " + sendMessage);

                //Get the return message from the server
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String message = br.readLine();
                System.out.println("Message received from the server : " + message);
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                //Closing the socket
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
