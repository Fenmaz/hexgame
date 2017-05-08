package hex;

import comp124graphics.*;
import comp124graphics.Rectangle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Primary game setup and logic.
 */
class HexGame extends CanvasWindow implements MouseListener {

    /* Private constants */
    private static final int CANVAS_WIDTH = 1000;
    private static final int CANVAS_HEIGHT = 800;

    private static final int NUM_HEX_ON_EDGE = 5;

    static final int FREE_HEX = 0;
    static final int FIRST_PLAYER = 1;
    static final int SECOND_PLAYER = 2;

    static final double HEX_RADIUS = 30;
    static final double HEX_HEIGHT = HEX_RADIUS * Math.sqrt(3) / 2;

    private static final int ANNOUNCEMENT_FONT_SIZE = 40;


    /* Private variables */
    private GameBoard board;

    private int turn;
    private boolean gameOver;
    private GraphicsText announcement;


    /**
     * Initialize the game board and mouse listeners. Player 1 moves first.
     */
    private HexGame() {

        super("Hex Game", CANVAS_WIDTH, CANVAS_HEIGHT);

        System.out.print(HEX_RADIUS + "\n");
        System.out.print(HEX_HEIGHT + "\n");

        board = new GameBoard(NUM_HEX_ON_EDGE);
        add(board, (CANVAS_WIDTH - board.getWidth()) / 2, (CANVAS_HEIGHT - board.getHeight()) / 2);
        addXO();

//        Rectangle rect = new Rectangle(board.getX(), board.getY(), board.getWidth(), board.getHeight());
//        add(rect);

        turn = FIRST_PLAYER;
        gameOver = false;

        announcement = new GraphicsText("Player 0 wins!", 0, 0);
        announcement.setFont(new Font("SanSerif", Font.BOLD, ANNOUNCEMENT_FONT_SIZE));
        // announcement.setColor(Color.BLUE);
        announcement.move((CANVAS_WIDTH - announcement.getWidth()) / 2, (CANVAS_HEIGHT - announcement.getHeight()));

        addMouseListener(this);


    }

    /**
     * When a hexagon is clicked, change the hex, check for winner and change turns.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!gameOver){
            GraphicsObject hex = board.getElementAt(e.getX(), e.getY());

            if (hex instanceof Hexagon && ((Hexagon) hex).getPlayer() == FREE_HEX) {
                ((Hexagon) hex).mark(turn);

                // Check if either player wins
                if (board.firstPlayerWin()) announceWinner(FIRST_PLAYER);
                if (board.secondPlayerWin()) announceWinner(SECOND_PLAYER);

                // Change turns
                if (turn == FIRST_PLAYER) turn = SECOND_PLAYER;
                else turn = FIRST_PLAYER;
            }
        }
        else {
            remove(announcement);
            remove(board);

            board = new GameBoard(NUM_HEX_ON_EDGE);
            add(board, (CANVAS_WIDTH - board.getWidth()) / 2, (CANVAS_HEIGHT - board.getHeight()) / 2);

            turn = FIRST_PLAYER;
            gameOver = false;
        }

    }

    /**
     * Announce the winner with a text in the middle of the screen
     */
    private void announceWinner(int winner) {
        announcement.setText("Player " + winner + " wins!");
        add(announcement);
        gameOver = true;
    }

    private void addXO() {
        double leftStartX = ((CANVAS_WIDTH - board.getWidth()) / 2)+ board.getWidth()/6;
        double upStartY = ((CANVAS_HEIGHT - board.getHeight()) / 2)+board.getHeight()/6;
        double rightStartX = ((CANVAS_WIDTH - board.getWidth()) / 2)+5*board.getWidth()/6;
        double downStartY = ((CANVAS_HEIGHT - board.getHeight()) / 2)+5*board.getHeight()/6;

        Line lineX1 = new Line(rightStartX, upStartY, rightStartX+15, upStartY+15);
        Line lineX2 = new Line (rightStartX, upStartY+15, rightStartX+15, upStartY);
        Line lineX3 = new Line(leftStartX+15, downStartY, leftStartX,  downStartY-15);
        Line lineX4 = new Line (leftStartX, downStartY, leftStartX+15, downStartY-15);
        Ellipse circleO1 = new Ellipse(leftStartX, upStartY, 15, 15);
        Ellipse circleO2 = new Ellipse(rightStartX, downStartY-15, 15, 15);
        add(lineX1);
        add(lineX2);
        add(lineX3);
        add(lineX4);
        add(circleO1);
        add(circleO2);
    }

    // Unused mouse listener methods
    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public static void main(String[] arg) {
        HexGame game = new HexGame();
    }

}

