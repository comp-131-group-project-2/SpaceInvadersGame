import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;

public class GameOver extends GraphicsObject {

    //image size is 40x32px
    Image image;

    public GameOver(int x, int y) {
        super(x, y);
        try {
            URL url = new URL("http://pixelartmaker.com/art/dabdcb017b41820.png");
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