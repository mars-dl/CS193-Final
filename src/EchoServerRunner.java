public class EchoServerRunner {
    public static void main() {
        IO.println("The way this program has been created, it will not allow you to return to the main menu." +
                " The only way to do that is by using Ctrl+C to end this session and start again with Bash run.sh.");
        String line = IO.readln("\nIf you still want to proceed, type PROCEED. Type anything else to go back: ");

        if (!line.equals("PROCEED")) {
            return;
        }
        IO.println("This is a demonstration of a simple server that can take multiple clients, show what they have sent it, and echo the same thing back." +
                "\nTo do this, open a SEPERATE terminal window and type bash runEchoClient.sh." +
                "\nYou can do this with as many windows as you'd like, and Ctrl+C in THIS terminal window to stop, and bash run.sh to start again.");
        EchoServer es = new EchoServer();
        es.startServer();
    }
}
