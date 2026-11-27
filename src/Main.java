void main() {

    //prints a simple menu for the user to navigate
    IO.println("Hello, and welcome to a repository of some of the projects I have created!");
    IO.println("Select action:\n1 - See Programs\n2 - Quit");

    //gets the action
    int action = integerInput();
    action = validateInput(action, 1, 2);

    //keeps the program running unless the user wants to quit
    while(action != 2) {
        //view programs that the user can run and interact with
        if (action == 1) {
            IO.println("\nThese are programs that I created as part of my first semester at Purdue, and most are fully runnable." +
                    "\nSelect program:" +
                    "\n1 - Basic Calculator" +
                    "\n2 - Pascal's Pyramid" +
                    "\n3 - Connect Four" +
                    "\n4 - Vet Database (Not Interactive)" +
                    "\n5 - Echo Server (Will end current session)" +
                    "\n6 - Basic Paint" +
                    "\n7 - Return to Menu");
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
            } else if (choice == 6) {
                try {
                    IO.println("A separate window should now open with the program. " +
                            "If you play with it, you can see that it is very buggy, but you can draw a line!" +
                            " And, if you fill the Hex or RGB fields. you can change the background color.");
                    Paint.main(null);
                } catch (Exception e) {
                    IO.println("Uh oh! Something went wrong. Sorry about that.");
                }
            }
        }
        //new iteration
        IO.println("\nSelect action:\n1 - See Programs\n2 - Quit");
        action = integerInput();
        action = validateInput(action, 1, 2);
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