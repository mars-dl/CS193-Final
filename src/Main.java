import java.io.BufferedReader;

void main() {

    //prints a simple menu for the user to navigate
    IO.println("Hello, and welcome to a repository of some of the projects I have created!");
    IO.println("Select action:\n1 - See Programs\n2 - See View-Only\n3 - Quit");

    //gets the action
    int action = integerInput();
    action = validateInput(action, 1, 3);

    //keeps the program running unless the user wants to quit
    while(action != 3) {
        //view programs that the user can run and interact with
        if (action == 1) {
            IO.println("\nThese are programs that I created as part of my first semester at Purdue, and most are fully runnable." +
                    "\nSelect program:" +
                    "\n1 - Basic Calculator" +
                    "\n2 - Pascal's Pyramid" +
                    "\n3 - Connect Four" +
                    "\n4 - Vet Database (Not Interactive)" +
                    "\n5 - Echo Server (Will end current session)" +
                    "\n6 - " +
                    "\n7 Return to Menu");
            int choice = integerInput();
            choice = validateInput(choice, 1, 7);
            //space for readability
            IO.println();

            if (choice == 1) {
                try {
                    BasicCalculator.main();
                } catch (Exception e) {
                    IO.println("Uh oh! Something went wrong. Sorry about that.");
                }
            } else if (choice == 2) {
                try {
                    PascalsPyramid.main();
                } catch (Exception e) {
                    IO.println("Uh oh! Something went wrong. Sorry about that.");
                }
            } else if (choice == 3) {
                try {
                    ConnectFour.main();
                } catch (Exception e) {
                    IO.println("Uh oh! Something went wrong. Sorry about that.");
                }
            } else if (choice == 4) {
                try {
                    VetDatabaseRunner.main();
                } catch (Exception e) {
                    IO.println("Uh oh! Something went wrong. Sorry about that.");
                }
            } else if (choice == 5) {
                try {
                    EchoServerRunner.main();
                } catch (Exception e) {
                    IO.println("Uh oh! Something went wrong. Sorry about that.");
                }
            }
            //view read-only programs
        } else if (action == 2) {
            IO.println("These are programs I created as part of Blue Valley's Center for Advanced Professional Studies. These cannot be run since they are written in C++." +
                    "\nSelect program:\n1 - IssueFinder (504 Lines)\n2 - Array Assignment(287 Lines)" +
                    "\n3 - Conditional Assignment (130 Lines)\n4 - Return to Menu");
            int choice = integerInput();
            choice = validateInput(choice, 1, 4);
            //space for readability
            IO.println();

            String line;
            if (choice == 1) {
                try(BufferedReader br = new BufferedReader(new FileReader("CS193-Final/CAPSFinalProject.txt"))) {
                    while((line = br.readLine()) != null) {
                        IO.println(line);
                    }
                } catch (IOException e) {
                    IO.println("Uh oh! Something went wrong. Sorry about that.");
                }
            } else if (choice == 2) {
                try(BufferedReader br = new BufferedReader(new FileReader("CS193-Final/CAPSArrayAssignment.txt"))) {
                    while((line = br.readLine()) != null) {
                        IO.println(line);
                    }
                } catch (IOException e) {
                    IO.println("Uh oh! Something went wrong. Sorry about that.");
                }
            } else if (choice == 3) {
                try(BufferedReader br = new BufferedReader(new FileReader("CS193-Final/CAPSConditionalAssignment.txt"))) {
                    while((line = br.readLine()) != null) {
                        IO.println(line);
                    }
                } catch (IOException e) {
                    IO.println("Uh oh! Something went wrong. Sorry about that.");
                }
            }
        }


        //new iteration
        IO.println("\nSelect action:\n1 - See Programs\n2 - See View-Only\n3 - Quit");
        action = integerInput();
        action = validateInput(action, 1, 3);
    }

}

//a method to return an integer without crashing the program
public int integerInput() {
    int toReturn;
    try {
        toReturn = Integer.parseInt(IO.readln("Action: "));
    } catch (NumberFormatException e) {
        IO.println("Error, please only enter a number");
        toReturn = integerInput();
    }
    return toReturn;
}

//a method to return an integer without crashing the program
public int validateInput(int action, int lo, int hi) {
    while (action < lo || action > hi) {
        IO.println("Error, invalid action");
        action = integerInput();
    }
    return action;
}