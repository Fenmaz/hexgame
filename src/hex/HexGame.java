package hex;

import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsObject;
import comp124graphics.GraphicsText;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Primary game setup and logic.
 */
class HexGame extends CanvasWindow implements MouseListener {

    /* Private constants */
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 1000;

    private static final int NUM_HEX_ON_EDGE = 11;

    static final int FREE_HEX = 0;
    static final int FIRST_PLAYER = 1;
    static final int SECOND_PLAYER = 2;

    static final double HEX_RADIUS = 10;
    static final double HEX_HEIGHT = HexGame.HEX_RADIUS * Math.sqrt(3) / 2;


    /* Private variables */
    private GameBoard board;

    private int turn;


    /**
     * Initialize the game board and mouse listeners. Player 1 moves first.
     */
    private HexGame() {

        super("Hex Game", CANVAS_WIDTH, CANVAS_HEIGHT);

        board = new GameBoard(NUM_HEX_ON_EDGE);

        add(board);

        turn = FIRST_PLAYER;

        addMouseListener(this);
    }

    /**
     * When a hexagon is clicked, change the hex, check for winner and change turns.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Hexagon hex = (Hexagon) getElementAt(e.getX(), e.getY());
        if (hex != null) {
            hex.mark(turn);

            // Check if either player wins
            if (board.firstPlayerWin()) announceWinner(FIRST_PLAYER);
            if (board.secondPlayerWin()) announceWinner(SECOND_PLAYER);

            // Change turns
            if (turn == FIRST_PLAYER) turn = SECOND_PLAYER;
            else turn = FIRST_PLAYER;
        }
    }

    /**
     * Announce the winner with a text in the middle of the screen
     */
    private void announceWinner(int winner) {
        GraphicsText announcement = new GraphicsText("Player " + winner + " wins!", 0, 0);
        announcement.move((CANVAS_WIDTH - announcement.getWidth()) / 2, (CANVAS_HEIGHT - announcement.getHeight()) / 2);
    }

    // Unused mouse listener methods
    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public static void main(String args[]) {
        HexGame game = new HexGame();
    }
}
