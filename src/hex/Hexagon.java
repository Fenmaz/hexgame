package hex;

import comp124graphics.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static hex.HexGame.HEX_HEIGHT;
import static hex.HexGame.HEX_RADIUS;


/**
 * Created by Trung Nguyen on 5/2/2017.
 *
 */
class Hexagon extends GraphicsGroup{

    private int player;
    private double x;
    private double y;

    private ArrayList<Hexagon> adjacent;

    private Point2D[] corners;


    Hexagon(double x, double y) {

        super(x, y);

        this.player = HexGame.FREE_HEX;

        this.x = x;
        this.y = y;

        this.adjacent = new ArrayList<>();
        this.corners = new Point2D[6];

        corners[0] = new Point2D.Double(x + HEX_RADIUS, y);
        corners[1] = new Point2D.Double(x + 0.5 * HEX_RADIUS, y - HEX_HEIGHT);
        corners[2] = new Point2D.Double(x - 0.5 * HEX_RADIUS, y - HEX_HEIGHT);
        corners[3] = new Point2D.Double(x - HEX_RADIUS, y);
        corners[4] = new Point2D.Double(x - 0.5 * HEX_RADIUS, y + HEX_HEIGHT);
        corners[5] = new Point2D.Double(x + 0.5 * HEX_RADIUS, y + HEX_HEIGHT);

        drawLine();
    }

    private void drawLine() {
        for (int i = 0; i < 5; i++) {
            Line line = new Line(corners[i].getX(), corners[i].getY(), corners[i + 1].getX(), corners[i + 1].getY());
            add(line);
        }

        Line line = new Line(corners[0].getX(), corners[0].getY(), corners[5].getX(), corners[5].getY());
        add(line);
    }

    void mark(int player) {
        if (player == HexGame.FIRST_PLAYER) xState();
        else if (player == HexGame.SECOND_PLAYER) oState();
    }

    /**
     * Draw x if the first player chooses the piece and mark the piece to be occupied by player 1.
     */
    private void xState() {
        Line lineX1 = new Line(x - 0.5 * HEX_RADIUS, y - 0.5 * HEX_HEIGHT, x + 0.5 * HEX_RADIUS, y + 0.5 * HEX_HEIGHT);
        Line lineX2 = new Line (x - 0.5 * HEX_RADIUS, y + 0.5 * HEX_HEIGHT, x + 0.5 * HEX_RADIUS, y - 0.5 * HEX_HEIGHT);
        add(lineX1);
        add(lineX2);
//        System.out.print("This piece is occupied by player "+occupied+".");
    }

    /**
     * Draw o if the second player chooses the piece and mark the piece to be occupied by player 2.
     */
    private void oState() {
        Ellipse circleO = new Ellipse(x - 0.5 * HEX_RADIUS, y - 0.5 * HEX_HEIGHT, HEX_RADIUS, HEX_RADIUS);
        add(circleO);
//        System.out.print("This piece is occupied by player "+occupied+".");
    }

    int getPlayer() { return this.player; }

    void addAdjacent(Hexagon hex) {
        this.adjacent.add(hex);
    }

    ArrayList<Hexagon> getAdjacent() {
        return this.adjacent;
    }

    public static void main(String[] arg) {
        CanvasWindow canvasWindow = new CanvasWindow("Test", 500, 500);
        Hexagon hex = new Hexagon(200,200);
        canvasWindow.add(hex);
        hex.oState();
    }

}
