import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;

public class WinPlane extends GraphicsObject {

    //image size is 40x32px
    Image image;

    public WinPlane(int x, int y) {
        super(x, y);

        super.speed_x = 0;
        super.speed_y = -7;
        try {
            URL url = new URL("https://piskel-imgstore-b.appspot.com/img/c42eec91-6266-11e9-881b-7b261314d9a0.gif");
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
}