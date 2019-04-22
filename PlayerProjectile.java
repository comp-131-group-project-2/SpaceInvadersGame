import java.awt.*;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class PlayerProjectile extends GraphicsObject {

    //image size is 40x32px
    Image image;
    int width;
    int height;

    public PlayerProjectile(int x, int y) {
        super(x, y);
        this.width = 8;
        this.height = 2;

        // hitbox
        BoundingBox = new Rectangle(x, y, width, height);

        // for some reason, "super" instead of "this" works
        // this sets the initial speed
        super.speed_x = 0;
        super.speed_y = -12;
        try {
            URL url = new URL("https://piskel-imgstore-b.appspot.com/img/8bf06259-64a4-11e9-ba8f-470f5731b29a.gif");
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() { return width; }

    public Rectangle getBoundingBox() { return BoundingBox; }

    public void draw(Graphics g) {
        g.drawImage(image, this.x, this.y, null);
    }
}
