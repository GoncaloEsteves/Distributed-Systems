import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String args[]) throws IOException {
        ServerSocket socket = new ServerSocket(12345);

        while (true) {
            Socket csocket = socket.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(csocket.getInputStream()));
            PrintWriter out = new PrintWriter(csocket.getOutputStream());

            String aux;
            while ((aux = in.readLine()) != null){
                out.println(aux);
                out.flush();
            }

            csocket.shutdownOutput();
            csocket.shutdownInput();
            csocket.close();
        }
    }
}
