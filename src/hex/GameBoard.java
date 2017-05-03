package hex;

import comp124graphics.GraphicsGroup;

import java.util.ArrayList;

/**
 * Created by Trung Nguyen on 5/2/2017.
 *
 * The game board.
 */
public class GameBoard extends GraphicsGroup {


    private ArrayList<Hexagon> allHexes;

    /**
     * Draw the game board.
     */
    GameBoard(int numHexOnEdge) {
        for (int i = 0; i < numHexOnEdge; i++) {
            for (int j = 0; j < numHexOnEdge; j++) {
                Hexagon hex = new Hexagon((i - j) * HexGame.HEX_HEIGHT, (i + j) * HexGame.HEX_RADIUS * 2);
                add(hex);
                allHexes.add(hex);
            }
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
