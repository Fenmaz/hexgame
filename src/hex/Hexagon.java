package hex;

import comp124graphics.*;
import comp124graphics.Rectangle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static hex.HexGame.HEX_HEIGHT;
import static hex.HexGame.HEX_RADIUS;

import static hex.HexGame.FREE_HEX;
import static hex.HexGame.FIRST_PLAYER;
import static hex.HexGame.SECOND_PLAYER;


/**
 * Created by Trung Nguyen on 5/2/2017.
 *
 */
class Hexagon extends GraphicsGroup implements MouseListener {

    private int player;

    private double x;
    private double y;

    private ArrayList<Hexagon> adjacent;

    private Point2D[] corners;

    Hexagon(double x, double y) {

        super(x, y);

        this.player = FREE_HEX;

        this.x = x;
        this.y = y;

        this.adjacent = new ArrayList<>();
        this.corners = new Point2D[6];

        corners[0] = new Point2D.Double(HEX_RADIUS, 0);
        corners[1] = new Point2D.Double(0.5 * HEX_RADIUS, - HEX_HEIGHT);
        corners[2] = new Point2D.Double(- 0.5 * HEX_RADIUS, - HEX_HEIGHT);
        corners[3] = new Point2D.Double(- HEX_RADIUS, 0);
        corners[4] = new Point2D.Double(- 0.5 * HEX_RADIUS, HEX_HEIGHT);
        corners[5] = new Point2D.Double(0.5 * HEX_RADIUS, HEX_HEIGHT);

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
        this.player = player;
        if (player == FIRST_PLAYER) xState();
        else if (player == SECOND_PLAYER) oState();
    }

    /**
     * Draw x if the first player chooses the piece and mark the piece to be occupied by player 1.
     */
    private void xState() {
        Line lineX1 = new Line(- 0.5 * HEX_RADIUS, - 0.5 * HEX_HEIGHT, 0.5 * HEX_RADIUS, 0.5 * HEX_HEIGHT);
        Line lineX2 = new Line (- 0.5 * HEX_RADIUS, + 0.5 * HEX_HEIGHT, + 0.5 * HEX_RADIUS, - 0.5 * HEX_HEIGHT);
        add(lineX1);
        add(lineX2);
    }

    /**
     * Draw o if the second player chooses the piece and mark the piece to be occupied by player 2.
     */
    private void oState() {
        Ellipse circleO = new Ellipse(- 0.5 * HEX_RADIUS, - 0.5 * HEX_HEIGHT, HEX_RADIUS, HEX_RADIUS);
        add(circleO);
    }

    int getPlayer() { return this.player; }

    void addAdjacent(Hexagon hex) {
        this.adjacent.add(hex);
    }

    ArrayList<Hexagon> getAdjacent() {
        return this.adjacent;
    }

    public boolean testHit(double x, double y, Graphics2D gc) {
        x -= this.x;
        y -= this.y;

        for (int i = 0; i < 5; i++) {
            double d = (x - corners[i].getX()) * (corners[i + 1].getY() - corners[i].getY())
                    - (y - corners[i].getY()) * (corners[i + 1].getX() - corners[i].getX());
            if (d < 0) {
                return false;
            }
        }

        return (x - corners[5].getX()) * (corners[0].getY() - corners[5].getY())
                - (y - corners[5].getY()) * (corners[0].getX() - corners[5].getX()) >= 0;
    }

    public static void main(String[] arg) {
        CanvasWindow canvasWindow = new CanvasWindow("Test", 500, 500);
        Hexagon hex = new Hexagon(200,200);
        canvasWindow.add(hex);
        hex.xState();
        hex.oState();

        canvasWindow.addMouseListener(hex);
    }

    public void mouseClicked(MouseEvent e) {
        System.out.print(this.getElementAt(e.getX(), e.getY()) + "\n");
    }

    // Unused mouse listener methods
    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
}
