import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws IOException {

        // System.setProperty("line.separator", "\r\n"); // ***** *** Mac users only *** *****

        ServerSocket server = new ServerSocket(1234);
        System.out.println("Server listens on port: " + server.getLocalPort());

        while(true) {
            Socket client = server.accept();
            new ServerThread(client).run();
        }

    }
}