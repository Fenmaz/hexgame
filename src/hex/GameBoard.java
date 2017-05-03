package hex;

import comp124graphics.GraphicsGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Trung Nguyen on 5/2/2017.
 *
 */
public class GameBoard extends GraphicsGroup {

    public static final int HEX_RADIUS = 10;
    public static final double HEX_HEIGHT = HEX_RADIUS * Math.sqrt(3) / 2;

    private ArrayList<Hexagon> allHexes;

    public GameBoard(int numHexOnEdge) {
        for (int i = 0; i < numHexOnEdge; i++) {
            for (int j = 0; j < numHexOnEdge; j++) {
                Hexagon hex = new Hexagon((i - j) * HEX_HEIGHT, (i + j) * HEX_RADIUS * 2);
                add(hex);
                allHexes.add(hex);
            }
        }
    }

    public boolean firstPlayerWin() {
        // TODO: Check if first player wins by a DFS on the hexagons starting from the SW region
        return false;
    }

    public boolean secondPlayerWin() {
        // TODO: Check if second player wins by a DFS on the hexagons starting from the NW region
        return false;
    }
}