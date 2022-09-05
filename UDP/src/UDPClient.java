import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws IOException {

        InetAddress serverAddress = InetAddress.getByName("172.21.48.178");
        int serverPort = 10007;

        String clientRequest = "432167";

        byte[] queryBuff = clientRequest.getBytes(); // or  String.valueOf()
        DatagramPacket query = new DatagramPacket(queryBuff, queryBuff.length, serverAddress, serverPort);

        DatagramSocket socket = new DatagramSocket();

        socket.send(query);
        System.out.println("Data to server: "  + clientRequest);

        DatagramPacket packet = new DatagramPacket(new byte[500], 500);

        System.out.println("Waiting for server response...");
        socket.receive(packet);

        String str = new String(packet.getData(), 0, packet.getLength()).trim();

        System.out.println("Received from server: "  + str);

        socket.close();
    }
}