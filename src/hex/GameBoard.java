package hex;

import comp124graphics.GraphicsGroup;

/**
 * Created by Trung Nguyen on 5/2/2017.
 *
 * The game board.
 */
public class GameBoard extends GraphicsGroup {


    private Hexagon[] allHexes;

    /**
     * Draw the game board.
     */
    GameBoard(int numHexOnEdge) {
        allHexes = new Hexagon[numHexOnEdge * numHexOnEdge];

        // Draw all hex and add them to an array.
        for (int i = 0; i < numHexOnEdge; i++) {
            for (int j = 0; j < numHexOnEdge; j++) {
                Hexagon hex = new Hexagon((i - j) * HexGame.HEX_HEIGHT, (i + j) * HexGame.HEX_RADIUS * 2);
                add(hex);
                allHexes[i * numHexOnEdge + j] = hex;
            }
        }

        // Add all adjacent hexagons to each hex.
        for (int i = 0; i < allHexes.length; i++) {
            Hexagon hex = allHexes[i];
            int[] possibleAdjacent = {i - 1, i + 1, i - numHexOnEdge, i + numHexOnEdge,
                    i - numHexOnEdge + 1, i + numHexOnEdge - 1};
            for (int j: possibleAdjacent) if (0 <= j && j < allHexes.length) hex.addAdjacent(allHexes[j]);

        }
    }

    /**
     * Check if the first player wins.
     */
    boolean firstPlayerWin() {
        // TODO: Check if first player wins by a DFS on the hexagons starting from the SW region
        return false;
    }

    /**
     * Check if the second player wins.
     */
    boolean secondPlayerWin() {
        // TODO: Check if second player wins by a DFS on the hexagons starting from the NW region
        return false;
    }
}
