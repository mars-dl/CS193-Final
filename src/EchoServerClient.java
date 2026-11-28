import java.io.*;
import java.net.*;

public class EchoServerClient {
    public void main() {
        //try-with-resources that connects to a server, and allows user to read and write from it
        try (Socket socket = new Socket("localhost", 1234);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
            String line;
            //repeatedly gets user input, sends it to the server, reads the response, and repeats
            //quits if the user sends EXIT
            while(!(line = IO.readln("What to send (EXIT to quit): ")).equals("EXIT")) {
                writer.println(line);
                IO.println("Server says: " + reader.readLine());
            }
        } catch (IOException e) {}
        IO.println("Finished");
    }
}
