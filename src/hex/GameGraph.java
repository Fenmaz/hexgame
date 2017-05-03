package hex;

/**
 * Created by Trung Nguyen on 5/2/2017.
 *
 */
public class GameGraph {

    private Boolean[][] adj_matrix;
    private GameBoard board;

    public GameGraph(GameBoard board) {

        this.board = board;

        adj_matrix = new Boolean[HexGame.NUM_HEX_ON_EDGE][HexGame.NUM_HEX_ON_EDGE];

    }
}
