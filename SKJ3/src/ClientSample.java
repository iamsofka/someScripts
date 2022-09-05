import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) {
//        https://gaia.cs.umass.edu/wireshark-labs/HTTP-wireshark-file1.html

        Instant time = Instant.now();
        System.out.println(time);
        try {
            InetAddress addr = InetAddress.getByName("gaia.cs.umass.edu");
            String ip = addr.getHostAddress();

            SocketAddress socketAddress = new InetSocketAddress(addr, 80);

            Socket socket = new Socket();
            socket.connect(socketAddress); // FOR 3WAY HANDSHAKE

            socket.setSoTimeout(10000); // FOR IO READING

            // FOR HTTP REQUEST
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // FOR HTTP RESPONSE
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("GET /wireshark-labs/HTTP-wireshark-file1.html HTTP/1.1");
            out.println("HOST: " + ip);
            out.println("Connection: close");
            out.println();

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            System.out.println("Sorry, socket timeout");
        } catch (IOException e) {
            e.printStackTrace();
        }

        long timer = ChronoUnit.MILLIS.between(time, Instant.now());
        System.out.println("TIME [ms]: " + timer);
    }
}