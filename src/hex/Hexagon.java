package hex;

import comp124graphics.*;

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


    Hexagon(double x, double y) {

        super(x, y);

        player = HexGame.FREE_HEX;

        this.x = x;
        this.y = y;

        adjacent = new ArrayList<>();

        Line line1 = new Line(x+ HEX_RADIUS, y, x+0.5* HEX_RADIUS,y- HEX_HEIGHT);
        Line line2 = new Line(x+0.5* HEX_RADIUS,y-HEX_HEIGHT, x-0.5*HEX_RADIUS,y-HEX_HEIGHT);
        Line line3 = new Line(x-0.5*HEX_RADIUS,y-HEX_HEIGHT, x-HEX_RADIUS,y);
        Line line4 = new Line(x-HEX_RADIUS, y, x-0.5*HEX_RADIUS,y+HEX_HEIGHT);
        Line line5 = new Line(x-0.5*HEX_RADIUS,y+HEX_HEIGHT, x+0.5*HEX_RADIUS,y+HEX_HEIGHT);
        Line line6 = new Line(x+0.5*HEX_RADIUS,y+HEX_HEIGHT, x+HEX_RADIUS, y);
        add(line1);
        add(line2);
        add(line3);
        add(line4);
        add(line5);
        add(line6);
    }

    void mark(int player) {
        if (player == HexGame.FIRST_PLAYER) xState();
        else if (player == HexGame.SECOND_PLAYER) oState();
    }

    /**
     * Draw x if the first player chooses the piece and mark the piece to be occupied by player 1.
     */
    private void xState() {
        Line lineX1 = new Line(x-0.5*HEX_RADIUS,y-0.5*HEX_HEIGHT,x+0.5*HEX_RADIUS,y+0.5*HEX_HEIGHT);
        Line lineX2 = new Line (x-0.5*HEX_RADIUS,y+0.5*HEX_HEIGHT,x+0.5*HEX_RADIUS,y-0.5*HEX_HEIGHT);
        add(lineX1);
        add(lineX2);
//        System.out.print("This piece is occupied by player "+occupied+".");
    }

    /**
     * Draw o if the second player chooses the piece and mark the piece to be occupied by player 2.
     */
    private void oState() {
        Ellipse circleO = new Ellipse(x-0.5*HEX_RADIUS,y-0.5*HEX_HEIGHT,HEX_RADIUS,HEX_RADIUS);
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
