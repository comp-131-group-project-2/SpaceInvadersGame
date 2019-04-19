import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Biplane extends GraphicsObject {

    //image size is 40x32px
    Image image;

    public Biplane(int x, int y) {
        super(x, y);
        try {
            URL url = new URL("https://piskel-imgstore-b.appspot.com/img/5b30d8a6-6204-11e9-bf18-ad8ec1d757c3.gif");
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