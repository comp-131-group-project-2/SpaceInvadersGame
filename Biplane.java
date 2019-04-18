import java.awt.Color;
import java.awt.Graphics;

public class Biplane extends GraphicsObject {

    Color color;
    int size_X;
    int size_Y;

    public Biplane(int x, int y) {
        super(x, y);
        this.color = new Color(96, 96, 96);
        this.size_X = 40;
        this.size_Y = 40;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(this.x, this.y, size_X, size_Y);
    }
}
