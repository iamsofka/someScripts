import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {

    public static void main(String[] args) throws IOException {
        try {
            DatagramSocket server = new DatagramSocket(1234);

            System.out.println("Waiting for request...");

            while (true) {
                DatagramPacket request = new DatagramPacket(new byte[500], 500);
                server.receive(request);


                new Thread(() -> {
                    System.out.println("NEW THREAD ID: " + Thread.currentThread().getId());


                    InetAddress clientAddress = request.getAddress();
                    int clientPort = request.getPort();

                    String requestText = new String(request.getData(), 0, request.getLength());


                    System.out.println("*** NEW REQUEST ***");
                    System.out.println("IP: " + clientAddress);
                    System.out.println("PORT: " + clientPort);
                    System.out.println("DATA: " + requestText);

                    String response = "ECHO " + requestText;

                    System.out.println("RESPONSE: " + response);

                    byte[] respBuff = response.getBytes(); // or  String.valueOf()
                    DatagramPacket resp = new DatagramPacket(respBuff, respBuff.length, clientAddress, clientPort);

                    try {
                        server.send(resp);
                    } catch (IOException e) {

                    }

                    System.out.println("*** END ***");

                    System.out.println("Waiting for next request...");

                }).start();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}