import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;

public class BiplaneProjectile extends GraphicsObject {

    //image size is 40x32px
    Image image;
    int width;
    int height;

    public BiplaneProjectile(int x, int y) {
        super(x, y);
        this.width = 8;
        this.height = 2;
        // for some reason, "super" instead of "this" works
        // this sets the initial speed
        super.speed_x = 0;
        super.speed_y = 10;
        try {
            URL url = new URL("https://piskel-imgstore-b.appspot.com/img/68c54ed9-6341-11e9-b33c-47f6622059b4.gif");
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() { return width; }

    public void draw(Graphics g) {
        g.drawImage(image, this.x, this.y, null);
    }
}
