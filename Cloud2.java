import java.awt.*;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class Cloud2 extends GraphicsObject {

    //image size is 32x32px
    Image image;
    int width;
    int height;

    public Cloud2(int x, int y) {
        super(x, y);
        this.width = 32;
        this.height = 32;
        // for some reason, "super" instead of "this" works
        // this sets the initial speed
        super.speed_x = -2;
        super.speed_y = 0;
        try {
            URL url = new URL("https://piskel-imgstore-b.appspot.com/img/88fa62d1-64bb-11e9-ab1e-05c32167680e.gif");
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, this.x, this.y, null);
        }
    }

    public void update(int pic_width, int pic_height, int frame) {
        // make ball bounce off the side of the window
        if (this.x <= -50) {
            this.x = 1280;
        }

        // let the superclass' update function handle the actual change to x and y
        super.update(pic_width, pic_height, frame);
    }
}