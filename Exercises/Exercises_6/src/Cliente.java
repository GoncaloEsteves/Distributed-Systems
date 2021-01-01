import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String args[]) throws IOException {
        Socket socket = new Socket("127.0.0.1", 12345);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());

        Scanner s = new Scanner(System.in);
        String aux;

        while ((aux = s.nextLine()) != null){
            out.println(aux);
            out.flush();
            System.out.println(in.readLine());
        }

        socket.shutdownOutput();
        socket.shutdownInput();
        socket.close();
    }
}
