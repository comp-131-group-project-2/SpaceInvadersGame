// utility
import java.util.ArrayList;
import java.util.Random;

// graphics
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

// events
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// swing
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SpaceInvaders extends JPanel implements ActionListener, KeyListener, Runnable {

    private final int canvasWidth;
    private final int canvasHeight;
    private final Color backgroundColor;

    private final int framesPerSecond = 25;
    private final int msPerFrame = 1000 / framesPerSecond;
    private Timer timer;
    private int frame = 0;

    public ArrayList<GraphicsObject> enemies = new ArrayList();
    public ArrayList<GraphicsObject> enemyProjectiles = new ArrayList();
    public ArrayList<GraphicsObject> gameOver = new ArrayList();
    public ArrayList<GraphicsObject> youWin = new ArrayList();

    // FIXME list your game objects here
    // create a list of aliens with a loop
    // remove aliens if they are hit using the update function

    /* Constructor for a Space Invaders game
     */
    public SpaceInvaders() {
        // fix the window size and background color
        this.canvasWidth = 1280;
        this.canvasHeight = 720;
        this.backgroundColor = new Color(0, 225, 255, 183);
        setPreferredSize(new Dimension(this.canvasWidth, this.canvasHeight));

        // set the drawing timer
        this.timer = new Timer(msPerFrame, this);

        // FIXME initialize your game objects
        // adding the grid of biplanes
        for (int i = 0; i <= 8; i++) {
            // i*50 is x separation
            for (int j = 0; j <= 4; j++) {
                // j * 50 is y separation
                this.enemies.add(new Biplane(i * 50, j * 50));
            }
        }
        for (int i = 0; i <= 3; i++) {
            this.enemyProjectiles.add(new BiplaneProjectile(
                    // x
                    (enemies.get(((int) (Math.random() * enemies.size())))).x,
                    // y
                    (enemies.get(((int) (Math.random() * enemies.size())))).y));
        }

        this.gameOver.add(new GameOver(200, 100));

        for (int i = 0; i <= 25; i++) {
            this.gameOver.add(new BiplaneRight(i * 50, 10));
        }
        for (int i = 0; i <= 25; i++) {
            this.gameOver.add(new BiplaneLeft(i * 50, 60));
        }

        this.youWin.add(new YouWin(100, 50));

        for (int i = 0; i <= 13; i++) {
            this.youWin.add(new WinPlane(180, i * 50));
        }
        for (int i = 0; i <= 13; i++) {
            this.youWin.add(new WinPlane(1100, i * 50));
        }
    }

    /* Start the game
     */
    @Override
    public void run() {
        // show this window
        display();

        // set a timer to redraw the screen regularly
        this.timer.start();
    }

    /* Create the window and display it
     */
    private void display() {
        JFrame jframe = new JFrame();
        jframe.addKeyListener(this);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setContentPane(this);
        jframe.pack();
        jframe.setVisible(true);
    }

    /* Run all timer-based events
     *
     * @param e  An object describing the timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // update the game objects
        update();
        // draw every object (this calls paintComponent)
        repaint(0, 0, this.canvasWidth, this.canvasHeight);
        // increment the frame counter
        this.frame++;
    }

    /* Paint/Draw the canvas.
     *
     * This function overrides the paint function in JPanel. This function is
     * automatically called when the panel is made visible.
     *
     * @param g The Graphics for the JPanel
     */
    @Override
    protected void paintComponent(Graphics g) {
        // clear the canvas before painting
        clearCanvas(g);
        if (hasWonGame()) {
            paintWinScreen(g);
        } else if (hasLostGame()) {
            paintLoseScreen(g);
        } else {
            paintGameScreen(g);
        }
    }

    /* Clear the canvas
     *
     * @param g The Graphics representing the canvas
     */
    private void clearCanvas(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(this.backgroundColor);
        g.fillRect(0, 0, this.canvasWidth, this.canvasHeight);
        g.setColor(oldColor);
    }

    /* Respond to key release events
     *
     * A key release is when you let go of a key
     *
     * @param e  An object describing what key was released
     */
    public void keyReleased(KeyEvent e) {
        // you can leave this function empty
    }

    /* Respond to key type events
     *
     * A key type is when you press then let go of a key
     *
     * @param e  An object describing what key was typed
     */
    public void keyTyped(KeyEvent e) {
        // you can leave this function empty
    }

    /* Respond to key press events
     *
     * A key type is when you press then let go of a key
     *
     * @param e  An object describing what key was typed
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // FIXME what happens when left arrow is pressed
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // FIXME what happens when right arrow is pressed
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            // FIXME what happens when space bar is pressed
        }
    }

    /* Update the game objects
     */
    private void update() {
        for (GraphicsObject obj : this.enemies) {
            obj.update(this.canvasWidth, this.canvasHeight, this.frame);
        }
        for (GraphicsObject obj : this.enemyProjectiles) {
            obj.update(this.canvasWidth, this.canvasHeight, this.frame);
            if (obj.y + obj.getHeight() > canvasHeight) {
                obj.x = enemies.get(((int) (Math.random() * enemies.size()))).x;
                obj.y = enemies.get(((int) (Math.random() * enemies.size()))).y;
            }
        }
        for (GraphicsObject obj : this.gameOver) {
            obj.update(this.canvasWidth, this.canvasHeight, this.frame);
            if (obj.getName().equals("BiplaneRight")) {
                if (obj.x >= canvasWidth) {
                    obj.x = 0;
                }
            }
            if (obj.getName().equals("BiplaneLeft")) {
                if (obj.x <= 0) {
                    obj.x = canvasWidth;
                    }
                }
            }
        for (GraphicsObject obj : this.youWin) {
            obj.update(this.canvasWidth, this.canvasHeight, this.frame);
                if (obj.y <= 0) {
                    obj.y = canvasHeight;
                }
            }
        }

    /* Check if the player has lost the game
     *
     * @returns  true if the player has lost, false otherwise
     */
    private boolean hasLostGame() {
        return false; // FIXME delete this when ready
    }

    /* Check if the player has won the game
     *
     * @returns  true if the player has won, false otherwise
     */
    private boolean hasWonGame() {
        return false; // FIXME delete this when ready
    }

    /* Paint the screen during normal gameplay
     *
     * @param g The Graphics for the JPanel
     */
    private void paintGameScreen(Graphics g) {
        for (GraphicsObject obj : this.enemies) {
            obj.draw(g);
        }
        for (GraphicsObject obj : this.enemyProjectiles) {
            obj.draw(g);
        }
    }

    /* Paint the screen when the player has won
     *
     * @param g The Graphics for the JPanel
     */
    private void paintWinScreen(Graphics g) {
        for (GraphicsObject obj : this.youWin) {
            obj.draw(g);
        }
    }

    /* Paint the screen when the player has lost
     *
     * @param g The Graphics for the JPanel
     */
    private void paintLoseScreen(Graphics g) {
        for (GraphicsObject obj : this.gameOver) {
            obj.draw(g);
        }
    }

    public static void main(String[] args) {
        SpaceInvaders invaders = new SpaceInvaders();
        EventQueue.invokeLater(invaders);
    }
}
