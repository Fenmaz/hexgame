package hex;

import comp124graphics.*;

import static hex.HexGame.HEX_HEIGHT;
import static hex.HexGame.HEX_RADIUS;


/**
 * Created by Trung Nguyen on 5/2/2017.
 *
 */
public class Hexagon extends GraphicsGroup{
    private int occupied;
    private double x;
    private double y;

    public Hexagon(double x, double y) {
        super(x, y);
        this.x = x;
        this.y = y;
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

    /**
     * Draw x if the first player chooses the piece and mark the piece to be occupied by player 1.
     */
    public void xState() {
        Line lineX1 = new Line(x-0.5*HEX_RADIUS,y-0.5*HEX_HEIGHT,x+0.5*HEX_RADIUS,y+0.5*HEX_HEIGHT);
        Line lineX2 = new Line (x-0.5*HEX_RADIUS,y+0.5*HEX_HEIGHT,x+0.5*HEX_RADIUS,y-0.5*HEX_HEIGHT);
        add(lineX1);
        add(lineX2);
        occupied = 1;
//        System.out.print("This piece is occupied by player "+occupied+".");
    }

    /**
     * Draw o if the second player chooses the piece and mark the piece to be occupied by player 2.
     */
    public void oState() {
        Ellipse circleO = new Ellipse(x-0.5*HEX_RADIUS,y-0.5*HEX_HEIGHT,HEX_RADIUS,HEX_RADIUS);
        add(circleO);
        occupied = 2;
//        System.out.print("This piece is occupied by player "+occupied+".");
    }

    public int getOccupied() {
        return occupied;
    }

    //    public static void main(String[] arg) {
//        CanvasWindow canvasWindow = new CanvasWindow("Test", 500, 500);
//        Hexagon hex = new Hexagon(200,200);
//        canvasWindow.add(hex);
//        hex.oState();
//    }


}
