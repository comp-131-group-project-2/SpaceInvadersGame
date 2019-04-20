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

    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, this.x, this.y, null);
        }
    }

    public void update(int pic_width, int pic_height, int frame) {
        //System.out.println(super.speed_x);
        // let the superclass' update function handle the actual change to x and y
        super.update(pic_width, pic_height, frame);
    }
}