public class CS180HW5 {
    /**
     * HW05 Pascals Pyramid
     *
     * A program to construct a pascal's pyramid of a specified layer and display a layer
     *
     * @author Ethan Ma, CRN SectionId: 10687-BLK
     * @version 9/27/26
     */

    int[][][] pyramid;

    void main() {
        initialize();

        int show = Integer.parseInt(IO.readln("Enter the layer to display:\n"));
        IO.println("Layer " + show);
        for (int row = 0; row < pyramid[show].length; row++) {
            for (int col = 0; col < pyramid[show][row].length; col++) {
                if (col == 0 && row != pyramid[show].length - 1) {
                    IO.print(pyramid[show][row][col] + " ");
                } else if (col != pyramid[show][row].length - 1 && row != pyramid[show].length - 1) {
                    IO.print(pyramid[show][row][col] + " ");
                } else {
                    IO.print(pyramid[show][row][col]);
                }
            }
            IO.println();
        }
    }


    /**
     * Asks the user for a number of layers, and creates a corresponding pyramid
     *
     */
    void initialize() {
        int numLayers = Integer.parseInt(IO.readln("Enter the number of layers:\n"));
        pyramid = new int[numLayers][][];
        for (int layer = 0; layer < pyramid.length; layer++) {
            int largestRowLength = layer + 1;
            pyramid[layer] = new int[largestRowLength][];

            for (int row = 0; row < pyramid[layer].length; row++) {
                pyramid[layer][row] = new int[largestRowLength - row];
            }
        }

        pyramid[0][0][0] = 1;
        pyramid[1][0][0] = 1;
        pyramid[1][0][1] = 1;
        pyramid[1][1][0] = 1;

        for (int layer = 2; layer < pyramid.length; layer++) {
            pyramid[layer][0][0] = 1;
            pyramid[layer][0][((pyramid[layer][0].length) - 1)] = 1;
            pyramid[layer][((pyramid[layer].length) - 1)][0] = 1;

            //populates the first row of that layer, other than the first and last columns
            for (int i = 1; i < pyramid[layer][0].length - 1; i++) {
                pyramid[layer][0][i] =
                        (pyramid[layer - 1][0][i - 1] + pyramid[layer - 1][0][i]);
            }

            //populates first value of every row in that layer, other than the first and last values
            for (int j = 1; j < pyramid[layer].length - 1; j++) {
                pyramid[layer][j][0] =
                        (pyramid[layer - 1][j - 1][0] + pyramid[layer - 1][j][0]);
            }

            //populates the "diagonal" of that layer, other than the first and last values
            for (int k = 1; k < pyramid[layer].length - 1; k++) {
                int lastIndex = pyramid[layer][k].length - 1;
                pyramid[layer][k][lastIndex] =
                        (pyramid[layer - 1][k - 1][lastIndex] +
                                pyramid[layer - 1][k][lastIndex - 1]);
            }

            //populates all of the remaining 0 values in each layer, except for the 2nd layer
            if (layer != 2) {
                for (int row = 1; row < pyramid[layer].length - 1; row++) {
                    for (int col = 1; col < pyramid[layer][row].length - 1; col++) {
                        pyramid[layer][row][col] =
                                (pyramid[layer - 1][row][col] + pyramid[layer - 1][row][col - 1] +
                                        pyramid[layer - 1][row - 1][col]);
                    }
                }
            }
        }
    }

}
