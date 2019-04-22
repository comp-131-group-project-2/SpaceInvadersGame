import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;

public class BiplaneLeft extends GraphicsObject {

    //image size is 40x32px
    Image image;

    public BiplaneLeft(int x, int y) {
        super(x, y);

        super.speed_x = -7;
        super.speed_y = 0;
        try {
            URL url = new URL("https://piskel-imgstore-b.appspot.com/img/2e12b41e-6254-11e9-8c47-1b2aa63331cb.gif");
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

    public String getName() {
        return "BiplaneLeft";
    }
}