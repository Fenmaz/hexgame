package hex;

import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsText;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HexGame extends CanvasWindow implements MouseListener {

    /* Private constants */
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 1000;

    static final int NUM_HEX_ON_EDGE = 11;

    private static final int FREE_HEX = 0;
    private static final int FIRST_PLAYER = 1;
    private static final int SECOND_PLAYER = 2;


    /* Private variables */
    private GameBoard board;

    private int turn;


    public HexGame() {

        super("Hex Game", CANVAS_WIDTH, CANVAS_HEIGHT);

        board = new GameBoard(NUM_HEX_ON_EDGE);

        turn = FIRST_PLAYER;

        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Hexagon hex = (Hexagon) getElementAt(e.getX(), e.getY());
        // TODO: associate the hex with the player at that turn

        // Change turns
        if (turn == FIRST_PLAYER) turn = SECOND_PLAYER;
        else turn = FIRST_PLAYER;

        // Check if either player wins
        if (board.firstPlayerWin()) announceWinner(FIRST_PLAYER);
        if (board.secondPlayerWin()) announceWinner(SECOND_PLAYER);

    }

    private void announceWinner(int winner) {
        // TODO: announce the winner
        GraphicsText announcement = new GraphicsText("Player ")
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
}
