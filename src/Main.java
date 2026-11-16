void main() {

    //prints a simple menu for the user to navigate
    IO.println("Hello, and welcome to a repository of some of the projects I have created!");
    IO.println("Select action:\n1 - See Runnables\n2 - See Demonstrations\n3 - See View-Only\n4 - Quit");

    //gets the action
    int action = integerInput();
    action = validateInput(action, 1, 4);

    //keeps the program running unless the user wants to quit
    while(action != 4) {

        //view programs that the user can run and interact with
        if (action == 1) {
            IO.println("Select program:\n1 - Basic Calculator\n2 - Pascal's Pyramid\n3 - Connect Four\n4 - Return to Menu");
            int choice = integerInput();
            choice = validateInput(choice, 1, 4);
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
            } if (choice == 3) {
                try {
                    ConnectFour.main();
                } catch (Exception e) {
                    IO.println("Uh oh! Something went wrong. Sorry about that.");
                }
            }
        }


        //new iteration
        IO.println("\nSelect action:\n1 - See Runnables\n2 - See Demonstrations\n3 - See View-Only\n4 - Quit");
        action = integerInput();
        action = validateInput(action, 1, 4);
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