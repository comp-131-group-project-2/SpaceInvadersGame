// utility
import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
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

//lists
import java.util.List;

public class SpaceInvaders extends JPanel implements ActionListener, KeyListener, Runnable {

    private final int canvasWidth;
    private final int canvasHeight;
    private final Color backgroundColor;

    private final int framesPerSecond = 25;
    private final int msPerFrame = 1000 / framesPerSecond;
    private Timer timer;
    private int frame = 0;

    //checks if game is running
    public boolean is_running;

    //lists of all the objects
    public List<GraphicsObject> enemies = new ArrayList();
    public ArrayList<GraphicsObject> enemyProjectiles = new ArrayList();
    public ArrayList<GraphicsObject> youWin = new ArrayList();
    public ArrayList<GraphicsObject> gameOver = new ArrayList();
    public GraphicsObject player;
    public ArrayList<GraphicsObject> clouds = new ArrayList();
    public ArrayList<GraphicsObject> clouds2 = new ArrayList();
    public ArrayList<GraphicsObject> playerProjectiles = new ArrayList<>();

    // enemy counter
    public int enemyCounter = 45;

    // enemy projectile index
    // purpose is to make sure there's enough biplanes for projectiles to spawn from
    int projIndex = 0;

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

        //says game is running
        is_running = true;

        // set the drawing timer
        this.timer = new Timer(msPerFrame, this);

        // INITIALIZING GAME OBJECTS
        // initialize game over screen
        this.gameOver.add(new GameOver(200, 100));
        for (int i = 0; i <= 25; i++) {
            this.gameOver.add(new BiplaneRight(i * 50, 10));
        }
        for (int i = 0; i <= 25; i++) {
            this.gameOver.add(new BiplaneLeft(i * 50, 60));
        }

        // initialize you win screen
        this.youWin.add(new YouWin(100, 50));
        for (int i = 0; i <= 13; i++) {
            this.youWin.add(new WinPlane(180, i * 50));
        }
        for (int i = 0; i <= 13; i++) {
            this.youWin.add(new WinPlane(1100, i * 50));
        }

        // GAMESTATE
        // initialize clouds
        for (int i = 0; i <= 4; i++) {
            this.clouds.add(new Cloud(((int) (Math.random() * canvasWidth)), ((int) (Math.random() * canvasHeight))));
        }
        for (int i = 0; i <= 2; i++) {
            this.clouds2.add(new Cloud2(((int) (Math.random() * canvasWidth)), ((int) (Math.random() * canvasHeight))));
        }

        // initialize the grid of biplanes (n = 45)
        for (int i = 0; i <= 8; i++) {
            // i*50 is x separation
            for (int j = 0; j <= 4; j++) {
                // j * 50 is y separation
                this.enemies.add(new Biplane(i * 50, j * 50));
            }
        }
        // initialize the enemies' projectiles (n = 4)
        for (int i = 0; i <= 3; i++) {
            this.enemyProjectiles.add(new BiplaneProjectile(
                    // x
                    (enemies.get(((int)(Math.random()*enemies.size()))).x),
                    // y
                    (enemies.get(((int)(Math.random()*enemies.size())))).y));
        }

        // initialize player
        player = new Player(canvasWidth/2, canvasHeight - (canvasHeight/5));

        // debug kill player bullet
        // enemyProjectiles.add(new BiplaneProjectile(player.x + 5, player.y - 300));

        // debug kill enemy bullet
        // playerProjectiles.add(new PlayerProjectile(400, 400));

        // debug enemy
        // this.enemies.add(new Biplane(player.x - 300, player.y));
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
        } else if (is_running == true) {
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
            player.moveLeft();
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.moveRight();
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.playerProjectiles.add(new PlayerProjectile(player.x + 20, player.y));
        }
    }

    /* Update the game objects
     */
    private void update() {
        // while game is running
        if (is_running == true) {
            // cloud
            for (GraphicsObject obj : this.clouds) {
                obj.update(this.canvasWidth, this.canvasHeight, this.frame);
            }
            // big cloud
            for (GraphicsObject obj : this.clouds2) {
                obj.update(this.canvasWidth, this.canvasHeight, this.frame);
            }
            // player
            player.update(this.canvasWidth, this.canvasHeight, this.frame);

            // enemies
            for (GraphicsObject enemy : this.enemies) {
                enemy.update(this.canvasWidth, this.canvasHeight, this.frame);
            }

            // checks to make sure there's enough enemies for enemy projectiles to spawn on
            if (enemies.size() < enemyProjectiles.size() && projIndex < 4) {
                // makes sure there's more biplanes than biplane projectiles
                System.out.println("reached " + projIndex);
                enemyProjectiles.remove(0);
                projIndex++;
            }

            // enemy projectiles
            for (GraphicsObject obj : this.enemyProjectiles) {
                obj.update(this.canvasWidth, this.canvasHeight, this.frame);
                for (int objIndex = 0; objIndex < this.enemyProjectiles.size(); objIndex++) {
                    if (obj.y + obj.getHeight() > canvasHeight) {
                        // spawns enemy's bullets on living enemies once enemy projectiles hit the end of screen
                        obj.y = enemies.get(((int) (Math.random() * enemies.size()))).y + 32;
                        obj.x = enemies.get(((int) (Math.random() * enemies.size()))).x + 20;
                    }
                }
            }

            // player projectiles
            for (int objIndex = 0; objIndex < this.playerProjectiles.size(); objIndex++) {
                this.playerProjectiles.get(objIndex).update(this.canvasWidth, this.canvasHeight, this.frame);
                for (int enemyIndex = 0; enemyIndex < this.enemies.size(); enemyIndex++) {
                    if ((enemies.get(enemyIndex).getBoundingBox().contains(playerProjectiles.get(objIndex).x,
                            playerProjectiles.get(objIndex).y))) {
                        // enemy is removed when hit
                        enemies.remove(enemyIndex);
                        enemyCounter -= 1;
                        // send it to oblivion
                        playerProjectiles.get(objIndex).y = -2000;
                    }
                }
                if (playerProjectiles.get(objIndex).y < -10) {
                    // if bullets exit the screen, remove them
                    playerProjectiles.remove(objIndex);
                }

            }
        }

        // when player loses game
        else if (hasLostGame()) {
            is_running = false;
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
        }

        // when player wins game
        else if (hasWonGame()) {
            is_running = false;
            for (GraphicsObject obj : this.youWin) {
                obj.update(this.canvasWidth, this.canvasHeight, this.frame);
                if (obj.y <= 0) {
                    obj.y = canvasHeight;
                }
            }
        }
    }

    /* Check if the player has lost the game
     *
     * @returns  true if the player has lost, false otherwise
     */
    private boolean hasLostGame() {
        for (GraphicsObject obj : this.enemyProjectiles) {
            if (player.getBoundingBox().contains(obj.x, obj.y)) {
                // if player is hit by an enemy projectile, player loses
                return true;
            }
        }
        for (GraphicsObject obj : this.enemies) {
            if (obj.y >= this.canvasHeight) {
                // if an enemy biplane reaches the bottom of the screen, player loses
                return true;
            }
            if (obj.getBoundingBox().contains(player.x, player.y)) {
                // if the player touches an enemy biplane, player loses
                return true;
            }
        }
        return false;
    }

    /* Check if the player has won the game
     *
     * @returns  true if the player has won, false otherwise
     */
    private boolean hasWonGame() {
        if (enemyCounter <= 0) {
            return true; }
        return false;
    }

    /* Paint the screen during normal gameplay
     *
     * @param g The Graphics for the JPanel
     */
    private void paintGameScreen(Graphics g) {
        if (is_running == true) {
            //cloud
            for (GraphicsObject obj : this.clouds) {
                obj.draw(g);
            }
            for (GraphicsObject obj : this.clouds2) {
                obj.draw(g);
            }
            //player
            player.draw(g);

            //enemies
            for (GraphicsObject obj : this.enemies) {
                obj.draw(g);
            }

            //enemy projectiles
            for (GraphicsObject obj : this.enemyProjectiles) {
                obj.draw(g);
            }

            //player projectiles
            for (GraphicsObject obj : this.playerProjectiles) {
                obj.draw(g);
            }
        }
    }

    /* Paint the screen when the player has won
     *
     * @param g The Graphics for the JPanel
     */
    private void paintWinScreen(Graphics g) {
        is_running = false;
        for (GraphicsObject obj : this.youWin) {
            obj.draw(g);
        }
    }

    /* Paint the screen when the player has lost
     *
     * @param g The Graphics for the JPanel
     */
    private void paintLoseScreen(Graphics g) {
        is_running = false;
        for (GraphicsObject obj : this.gameOver) {
            obj.draw(g);
        }
    }

    public static void main(String[] args) {
        SpaceInvaders invaders = new SpaceInvaders();
        EventQueue.invokeLater(invaders);
    }
}
