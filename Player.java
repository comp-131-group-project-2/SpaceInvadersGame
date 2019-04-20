import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GraphicsObject {

    int width;
    int height;
    Color color;

    public Player(int x, int y, int width, int height, Color color) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /* Draw the rectangle
     *
     * @param g The Graphics for the JPanel
     */
    @Override
    public void draw(Graphics g) {
        // change the color of the pen
        g.setColor(this.color);
        // draw the rectangle
        g.fillRect(this.x, this.y, this.width, this.height);
    }
}
