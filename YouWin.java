import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;

public class YouWin extends GraphicsObject {

    //image size is 40x32px
    Image image;
    Image scaled;

    public YouWin(int x, int y) {
        super(x, y);
        try {
            URL url = new URL("http://yesiamcheap.com/wordpress/wp-content/uploads/2012/12/youwon1.gif");
            image = ImageIO.read(url);
            scaled = image.getScaledInstance(1150, 600, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        if (scaled != null) {
            g.drawImage(scaled, this.x, this.y, null);
        }
    }
}