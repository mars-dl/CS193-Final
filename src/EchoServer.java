import java.net.*;
import java.io.*;

public class EchoServer {
    private int connections = 0;
    private final Object LOCK = new Object();
    private boolean running = true;

    public int getConnections() {
        return connections;
    }

    public void endServer() {
        running = false;
    }

    public void startServer() {
        try (ServerSocket server = new ServerSocket(1234)) {
            while (running) {
                try {
                    Socket socket = server.accept();
                    ClientHandler ch = new ClientHandler(socket);
                    Thread t = new Thread(ch);
                    t.start();
                } catch (IOException e) {}
            }
        } catch (IOException e) {}
    }

    private class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            synchronized (LOCK) {
                connections++;
                IO.println("Connections: " + connections);
            }
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
                while (true) {
                    String line = reader.readLine();
                    if(line != null) {
                        if (line.equals("EXIT")) {
                            writer.println("EXIT");
                            synchronized (LOCK) {
                                connections--;
                                IO.println("Connections: " + connections);
                            }
                            break;
                        } else {
                            IO.println("A connection says: " + line);
                            writer.println(line);
                        }
                    }
                }
            } catch (IOException e) {}
        }
    }
}