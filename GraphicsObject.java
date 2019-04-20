import java.awt.Color;
import java.awt.Graphics;

public class GraphicsObject {

    int x;
    int y;
    int speed_x;
    int speed_y;

    public GraphicsObject(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed_x = 0;
        this.speed_y = 0;
    }

    public int getHeight() {
        return 0;
    }

    public String getName() {
        return "GraphicsObject";
    }

    /* Draw the object
     *
     * This function should never be called directly, but should be overridden
     * by subclasses.
     *
     * @param g The Graphics for the JPanel
     */
    public void draw(Graphics g) {
    }

    /* Update the object's location based on its speed
     *
     * @param pic_width   The width of the drawing window
     * @param pic_height  The height of the drawing window
     * @param frame       The number of frames since the start of the program
     */
    public void update(int pic_width, int pic_height, int frame) {
        this.x += this.speed_x;
        this.y += this.speed_y;
    }
}
