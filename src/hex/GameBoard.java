package hex;

import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsGroup;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import static hex.HexGame.HEX_HEIGHT;
import static hex.HexGame.HEX_RADIUS;

/**
 * Created by Trung Nguyen on 5/2/2017.
 *
 * The game board.
 */
class GameBoard extends GraphicsGroup {

    private Set<Hexagon> NE_region;
    private Set<Hexagon> SE_region;
    private Set<Hexagon> NW_region;
    private Set<Hexagon> SW_region;

    private Hexagon[] allHexes;
    private int numHexOnEdge;


    /**
     * Draw the game board.
     */
    GameBoard(int numHexOnEdge) {

        super();

        this.numHexOnEdge = numHexOnEdge;
        this.allHexes = new Hexagon[numHexOnEdge * numHexOnEdge];

        NE_region = new HashSet<>();
        SE_region = new HashSet<>();
        NW_region = new HashSet<>();
        SW_region = new HashSet<>();

        // Draw all hex and add them to an array.
        for (int i = 0; i < numHexOnEdge; i++) {
            for (int j = 0; j < numHexOnEdge; j++) {
                Hexagon hex = new Hexagon((i + j + 1) * HEX_RADIUS * 1.5 / 2, (numHexOnEdge + j - i) * HEX_HEIGHT / 2);
                add(hex);
                this.allHexes[i * numHexOnEdge + j] = hex;
            }
        }

        populateRegions();
        addAdjacentHex();

    }


    /**
     * Add all adjacent hexagons to each hex.
     */
    private void addAdjacentHex() {
        for (int i = 0; i < numHexOnEdge * numHexOnEdge; i++) {
            Hexagon hex = allHexes[i];
            int[] possibleAdjacent = {i - 1, i + 1, i - numHexOnEdge, i + numHexOnEdge,
                    i - numHexOnEdge + 1, i + numHexOnEdge - 1};
            for (int j: possibleAdjacent) if (0 <= j && j < allHexes.length) hex.addAdjacent(allHexes[j]);

        }
    }

    /**
     * Populate the region sets with corresponding hexagons.
     */
    private void populateRegions() {
        for (int i = 0; i < numHexOnEdge; i++) {
            SW_region.add(allHexes[i]);
            NW_region.add(allHexes[i * numHexOnEdge]);
            NE_region.add(allHexes[numHexOnEdge * numHexOnEdge - 1 - i]);
            SE_region.add(allHexes[numHexOnEdge * (i + 1) - 1]);
        }
    }

    /**
     * Check if the first player wins, using a DFS from the
     */
    boolean firstPlayerWin() {
//        Stack<Hexagon> stack = new Stack<>();
//        for (Hexagon hex: SW_region)
//            if (hex.player() == HexGame.FIRST_PLAYER)
//                stack.add(hex);

        Stack<Hexagon> stack = SW_region.stream()
                .filter(hexagon -> hexagon.getPlayer() == HexGame.FIRST_PLAYER)
                .collect(Collectors.toCollection(Stack::new));

        HashSet<Hexagon> visited = new HashSet<>();

        while (!stack.empty()) {
            Hexagon last = stack.pop();
            for (Hexagon hex: last.getAdjacent()) {
                if (hex.getPlayer() == HexGame.FIRST_PLAYER && !visited.contains(hex)) {
                    if (NE_region.contains(hex)) return true;
                    visited.add(hex);
                    stack.add(hex);
                }
            }

        }
        return false;
    }

    /**
     * Check if the second player wins.
     */
    boolean secondPlayerWin() {
        Stack<Hexagon> stack = NW_region.stream()
                .filter(hexagon -> hexagon.getPlayer() == HexGame.SECOND_PLAYER)
                .collect(Collectors.toCollection(Stack::new));

        HashSet<Hexagon> visited = new HashSet<>();

        while (!stack.empty()) {
            Hexagon last = stack.pop();
            for (Hexagon hex: last.getAdjacent()) {
                if (hex.getPlayer() == HexGame.SECOND_PLAYER && !visited.contains(hex)) {
                    if (SE_region.contains(hex)) return true;
                    visited.add(hex);
                    stack.add(hex);
                }
            }

        }
        return false;
    }

    public static void main(String[] arg) {
        CanvasWindow canvas = new CanvasWindow("GameBoard", 1000, 700);
        GameBoard board = new GameBoard(11);
        canvas.add(board);
    }

}
