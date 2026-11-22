import java.io.*;
import java.net.*;

public class EchoServerClient {
    public void main() {
        try (Socket socket = new Socket("localhost", 1234);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
            String line;
            while(!(line = IO.readln("What to send (EXIT to quit): ")).equals("EXIT")) {
                writer.println(line);
                IO.println("Server says: " + reader.readLine());
            }
        } catch (IOException e) {
            IO.println("Finished");
        }
    }
}
