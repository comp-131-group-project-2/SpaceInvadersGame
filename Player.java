import java.awt.*;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Player extends GraphicsObject {

    //image size is 40x32px
    Image image;
    int width;
    int height;

    public Player(int x, int y) {
        super(x, y);
        this.width = 40;
        this.height = 32;

        // hitbox
        BoundingBox = new Rectangle(x, y, width, height);

        // for some reason, "super" instead of "this" works
        // this sets the initial speed
        super.speed_x = 0;
        super.speed_y = 0;
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

    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public Rectangle getBoundingBox() { return BoundingBox; }
    public void moveRight(){
        if(x < 1235){
            x += 5;
        }
    }
    public void moveLeft(){
        if(x > 5){
            x -= 5;
        }
    }

    public void update(int pic_width, int pic_height, int frame) {
        // make player stay in the window
        if (this.x < 0 || this.x + this.width > pic_width) {

        }

        // hitbox moves with player
        BoundingBox.x = this.x;
        BoundingBox.y = this.y;

        // let the superclass' update function handle the actual change to x and y
        super.update(pic_width, pic_height, frame);
    }
}