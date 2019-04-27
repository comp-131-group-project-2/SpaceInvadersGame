import java.awt.*;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class Biplane extends GraphicsObject {

    //image size is 40x32px
    Image image;
    int width;
    int height;

    public Biplane(int x, int y) {
        super(x, y);
        this.width = 40;
        this.height = 32;

        // hitbox
        BoundingBox = new Rectangle(x, y, width, height);

        // for some reason, "super" instead of "this" works
        // this sets the initial speed
        super.speed_x = 6;
        super.speed_y = 0;
        try {
            URL url = new URL("https://piskel-imgstore-b.appspot.com/img/5b30d8a6-6204-11e9-bf18-ad8ec1d757c3.gif");
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public Rectangle getBoundingBox() { return BoundingBox; }
    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, this.x, this.y, null);
        }
    }

    public void update(int pic_width, int pic_height, int frame){
        // make biplanes stay in the window and descend
        if (this.x < 0 || this.x + this.width > pic_width) {
            this.speed_x = -this.speed_x;
            this.y += 72;
        }
        if (this.y + this.height > pic_height) {
            //game ends
        }

        // keep biplane's hitbox with biplane as it moves
        BoundingBox.x = this.x;
        BoundingBox.y = this.y;

        // let the superclass' update function handle the actual change to x and y
        super.update(pic_width, pic_height, frame);
    }
}