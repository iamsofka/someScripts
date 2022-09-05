import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// or implements Runnable class
public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String thread_ID = Long.toString(currentThread().getId());
        System.out.println("THREAD " + thread_ID + " entering");

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String line;
            String echo_line;
            line = in.readLine();

            if (line != null) {
                System.out.println("THREAD " + thread_ID + " received: " + line);
                echo_line = "ECHO: " + line;
                System.out.println("THREAD " + thread_ID + " returning: " + echo_line);
                out.println(echo_line);
            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}