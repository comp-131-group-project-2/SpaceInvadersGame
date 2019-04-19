import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Biplane extends GraphicsObject {

    //image size is 40x32px
    Image image;
    int width;
    int height;

    public Biplane(int x, int y) {
        super(x, y);
        this.width = 40;
        this.height = 32;
        // for some reason, "super" instead of "this" works
        // this sets the initial speed
        super.speed_x = 7;
        super.speed_y = 0;
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

    public void update(int pic_width, int pic_height, int frame) {
        // make biplanes bounce off the side of the window
        if (this.x < 0 || this.x + this.width > pic_width) {
            super.speed_x = -super.speed_x;
            this.y += 32;
        }
        if (this.y < 0 || this.y + this.height > pic_height) {
            super.speed_y = -super.speed_y;
        }
        //System.out.println(super.speed_x);
        // let the superclass' update function handle the actual change to x and y
        super.update(pic_width, pic_height, frame);
    }
}