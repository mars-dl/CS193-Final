public class CS180HW4 {
    /**
     * HW04 Basic Calculator
     *
     * A program which allows user to repeatedly enter mathematical expressions and calculate result
     *
     * @author Ethan Ma, CRN SectionId: 10687-BLK
     * @version 9/18/26
     */
    void main() {
        //menu
        IO.println("Welcome to Basic Calculator!");
        IO.println("1 - Enter an Equation\n2 - Exit");

        int menuChoice = Integer.parseInt(IO.readln());
        while (menuChoice != 1 && menuChoice != 2) {
            IO.println("Invalid Menu Option");
            menuChoice = Integer.parseInt(IO.readln("1 - Enter an Equation\n2 - Exit\n"));
        }

        String eq = "";
        while (menuChoice != 2) {
            eq = IO.readln("Enter an equation:\n");

            while (containsOperator(eq)) {
                int first = indexOfFirstOrderOperator(eq);
                int prev = indexOfPrevOperator(eq, first);
                int next = indexOfNextOperator(eq, first);
                eq = calculate(eq, prev, first, next);
            }
            IO.println(String.format("Solution = %1$3.2f", Double.parseDouble(eq)));

            menuChoice = Integer.parseInt(IO.readln("1 - Enter an Equation\n2 - Exit\n"));
        }
        IO.println("Thank you for using Basic Calculator!");
    }

        /**
         * checks if eq contains any mathematical operator sign
         * @param String eq is the equation
         * @return true or false if it contains a operator sign
         */
        boolean containsOperator(String eq) {
            if ((eq.indexOf("+") != -1 ||
                    eq.indexOf("-") != -1 ||
                    eq.indexOf("*") != -1 ||
                    eq.indexOf("/") != -1 ||
                    eq.indexOf("^") != -1)) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * finds the index of the first operator sign in PEMDAS order
         * @param String eq is the equation
         * @return index of the first operator sign, ^, *, /, +, -
         */
        int indexOfFirstOrderOperator(String eq) {
            int indexPow = eq.indexOf("^");
            int indexMul = eq.indexOf("*");
            int indexDiv = eq.indexOf("/");
            int indexAdd = eq.indexOf("+");
            int indexSub = eq.indexOf("-");
            if (indexPow != -1) {
                return indexPow;
            } else if (indexMul != -1 || indexDiv != -1) {
                if (indexMul != -1 && (indexMul < indexDiv || indexDiv == -1)) {
                    return indexMul;
                } else if (indexDiv != -1) {
                    return indexDiv;
                }
            } else if (indexAdd != -1 || indexSub != -1) {
                if (indexAdd != -1 && (indexAdd < indexSub || indexSub == -1)) {
                    return indexAdd;
                } else if (indexSub != -1) {
                    return indexSub;
                }
            }
            return -1;
        }

        /**
         * finds the index of the previous mathematic operator: +, -, *, /, returns that index
         * 0 if there is no other operator, ^ is not included because there are no sequential exponents
         * @param String eq is the equation
         * @param int from is the index of the current operator sign, will be where it starts the search
         * @return index of the previous operator sign
         */
        int indexOfPrevOperator(String eq, int from) {
            int lastAdd = eq.lastIndexOf("+", from - 1);
            int lastSub = eq.lastIndexOf("-", from - 1);
            int lastMul = eq.lastIndexOf("*", from - 1);
            int lastDiv = eq.lastIndexOf("/", from - 1);
            int last = Math.max(Math.max(lastAdd, lastSub), Math.max(lastMul, lastDiv));
            if (last != -1) {
                return last;
            } else {
                return 0;
            }

        }

        /**
         * finds the index of the next mathematic operator: +, -, *, /, returns that index
         * returns the length of the equation if there is no other operator
         * @param String eq is the equation
         * @param int from is the index of the current operator sign, will be where it starts the search
         * @return index of the next operator sign
         */
        int indexOfNextOperator(String eq, int from) {
            int nextAdd = eq.indexOf("+", from + 1);
            if (nextAdd == -1) {
                nextAdd = Integer.MAX_VALUE;
            }
            int nextSub = eq.indexOf("-", from + 1);
            if (nextSub == -1) {
                nextSub = Integer.MAX_VALUE;
            }
            int nextMul = eq.indexOf("*", from + 1);
            if (nextMul == -1) {
                nextMul = Integer.MAX_VALUE;
            }
            int nextDiv = eq.indexOf("/", from + 1);
            if (nextDiv == -1) {
                nextDiv = Integer.MAX_VALUE;
            }
            int next = Math.min(Math.min(nextAdd, nextSub), Math.min(nextMul, nextDiv));
            if (next < Integer.MAX_VALUE) {
                return next;
            } else {
                return eq.length();
            }
        }

        /**
         * takes the equation, modifies it
         * removes the number bounded by the previous, current, and next mathematical operator
         * calculates the correct answer and inserts it back into the equation
         * @param String eq is the equation
         * @param int prev is the index of the previous operator sign
         * @param int first is the index of the current operator sign
         * @param int next is the idnex of the next operator sign
         * @return String eq is the modified version of the old eq
         */
        String calculate(String eq, int prev, int first, int next) {
            double prevNum;
            double nextNum;
            double result;

            if (prev == 0) {
                prevNum = Double.parseDouble(eq.substring(prev, first));
            } else {
                prevNum = Double.parseDouble(eq.substring(prev + 1, first));
            }
            //the reason for this if statement is if there is no previous operator, prev is = 0
            //so eq.substring(0, prev + 1) would return the first character of the eq
            //even though it should be blank

            nextNum = Double.parseDouble(eq.substring(first + 1, next));

            if (eq.charAt(first) == '^') {
                result = Math.pow(prevNum, nextNum);
            } else if (eq.charAt(first) == '*') {
                result = (prevNum * nextNum);
            } else if (eq.charAt(first) == '/') {
                result = (prevNum / nextNum);
            } else if (eq.charAt(first) == '+') {
                result = (prevNum + nextNum);
            } else {
                result = (prevNum - nextNum);
            }

            if (prev == 0) {
                //the reason for this is the same reason as detailed above
                return (result + eq.substring(next));
            } else {
                return (eq.substring(0, prev + 1) + result + eq.substring(next));
            }
        }
}
