public class ConnectFour {
    /**
     * PJ01 Connect Four
     *
     * A program to allow two players to play a complete game of connect four
     *
     * @author Ethan Ma, CRN SectionId: 10687-BLK
     * @version 9/26/26
     */

    static String[][] board = new String[6][7];
    static int player = 1;
    //the index of the last dropped peice
    static int x;
    static int y;
    //the current winner, no winner is 0
    static int winner = 0;

    static void main() {
        initialize();
        IO.println("Welcome to Connect Four!");
        printBoard();
        while (winner == 0 && !checkFull()) {
            dropPeice();
            checkRow();
            checkCol();
            checkPositiveDiagonal();
            checkNegativeDiagonal();
            nextTurn();
        }
        if (winner == 0) {
            IO.println("Game Ends in a Draw!");
        } else {
            IO.println("Player " + winner + " Wins!");
        }
    }

    /**
     * Prints the board along with numbered columns
     */
    static void printBoard() {
        IO.println("1 2 3 4 5 6 7");
        String print = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                print += board[i][j] + " ";
            }
            IO.println(print.trim());
            print = "";
        }
    }

    /**
     * Drops a peice onto the board, also keeps track of the index of the last added peice
     */
    static void dropPeice() {
        String peice = "";
        if (player == 1) {
            IO.print("Player 1 (X) - ");
            peice = "X";
        } else if (player == 2) {
            IO.print("Player 2 (O) - ");
            peice = "O";
        }
        int col = (Integer.parseInt(IO.readln("Choose a column:\n"))) - 1;
        while ((col < 0 || col > 6) || !(board[0][col]).equals(".")) {
            IO.println("Invalid Column");
            if (player == 1) {
                IO.print("Player 1 (X) - ");
                peice = "X";
            } else if (player == 2) {
                IO.print("Player 2 (O) - ");
                peice = "O";
            }
            col = (Integer.parseInt(IO.readln("Choose a column:\n"))) - 1;
        }
        x = col;
        for (int row = 1; row < 6; row++) {
            if (!(board[row][col]).equals(".")) {
                board[row - 1][col] = peice;
                y = row - 1;
                break;
            } else if (row == 5) {
                board[row][col] = peice;
                y = row;
            }
        }
        printBoard();
    }

    /**
     * Checks the entire row (y) of the added peice to see if there are four of that peice in a row
     * If above is true, winner is set to the current player
     */
    static void checkRow() {
        String line = "";
        String peice = board[y][x];
        int inRow = 0;
        for (String i : board[y]) {
            line += i;
        }

        for (int j = line.indexOf(peice); j < line.length(); j++) {
            if (line.substring(j, j + 1).equals(peice)) {
                inRow++;
            } else {
                inRow = 0;
            }
            if (inRow == 4) {
                winner = player;
                break;
            }
        }
    }

    /**
     * Checks the entire column (x) of the added peice to see if there are four of that peice in a row
     * If above is true, winner is set to the current player
     */
    static void checkCol() {
        String line = "";
        String peice = board[y][x];
        int inRow = 0;
        for (int i = 0; i < board.length; i++) {
            line += board[i][x];
        }

        for (int j = line.indexOf(peice); j < line.length(); j++) {
            if (line.substring(j, j + 1).equals(peice)) {
                inRow++;
            } else {
                inRow = 0;
            }
            if (inRow == 4) {
                winner = player;
                break;
            }
        }
    }

    /**
     * Checks the positive diagional (bottom left to top right) of the added peice
     * If above is true, winner is set to the current player
     */
    static void checkPositiveDiagonal() {
        String line = "";
        String left = "";
        String peice = board[y][x];
        int inRow = 0;

        int i = y + 1;
        int k = x - 1;
        while (i < board.length && k >= 0) {
            left += board[i++][k--];
        }
        for (int l = left.length(); l > 0; l--) {
            line += left.substring(l - 1, l);
        }
        line += peice;
        i = y - 1;
        k = x + 1;
        while (i >= 0 && k <= board[0].length - 1) {
            line += board[i--][k++];
        }

        for (int j = line.indexOf(peice); j < line.length(); j++) {
            if (line.substring(j, j + 1).equals(peice)) {
                inRow++;
            } else {
                inRow = 0;
            }
            if (inRow == 4) {
                winner = player;
                break;
            }
        }
    }

    /**
     * Checks the negative diagional (top left to bottom right) of the added peice
     * If above is true, winner is set to the current player
     */
    static void checkNegativeDiagonal() {
        String line = "";
        String left = "";
        String peice = board[y][x];
        //line += peice;
        int inRow = 0;

        int i = y - 1;
        int k = x - 1;
        while (i > 0 && k >= 0) {
            left += board[i--][k--];
        }
        for (int l = left.length(); l > 0; l--) {
            line += left.substring(l - 1, l);
        }
        line += peice;
        i = y + 1;
        k = x + 1;
        while (i < board.length && k < board[0].length) {
            line += board[i++][k++];
        }

        for (int j = line.indexOf(peice); j < line.length(); j++) {
            if (line.substring(j, j + 1).equals(peice)) {
                inRow++;
            } else {
                inRow = 0;
            }
            if (inRow == 4) {
                winner = player;
                break;
            }
        }
    }

    /**
     * Swaps turn
     */
    static void nextTurn() {
        if (player == 1) {
            player = 2;
        } else {
            player = 1;
        }
    }

    /**
     * Checks if every space on the board is full by checking the first row
     * will return false if not full
     *
     * @return firstLineFull if the first row has no "." in it
     */
    static boolean checkFull() {
        boolean firstLineFull = true;
        for (String i : board[0]) {
            if (i.equals(".")) {
                firstLineFull = false;
            }
        }
        return firstLineFull;
    }

    /**
     * Fills the board, should only be run once
     */
    static void initialize() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ".";
            }
        }
    }
}
