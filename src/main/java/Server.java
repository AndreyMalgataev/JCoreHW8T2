import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                Socket connection = serverSocket.accept();

                PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                System.out.printf("New connection accepted. Port: %d%n", connection.getPort());
                out.println("Write your name.");
                final String name = in.readLine();

                out.println("Are you child? (yes/no)");
                final boolean isChild = in.readLine().equals("yes");

                if (isChild) {
                    out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                } else {
                    out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!"
                            , name));
                }
            }
        } catch (
                IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
