import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor2 {

    public static void main(String args[]) throws IOException {
        ServerSocket socket = new ServerSocket(12345);
        List<Thread> threads = new ArrayList<>();

        while (true) {
            Socket csocket = socket.accept();
            Thread t = new Thread(new SThread(csocket));
            threads.add(t);
            t.start();
        }
    }
}