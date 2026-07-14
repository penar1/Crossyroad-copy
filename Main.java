import javax.imageio.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;

public class Main extends JPanel implements Runnable {
    static World world;
    static Player player;
    static Zug zug;

    public static final int FACTOR = 6;
    public static final int TILE = 16 * FACTOR;
    public static final int LANE_COUNT = 7;
    public static final int WINDOW_WIDTH = 200 * FACTOR;
    public static final int WINDOW_HEIGHT = TILE * LANE_COUNT;
    private volatile boolean moveUpRequested = false;

    private Image Frosch;
    private Image Frosche;
    public Image Gras;
    public Image AutoBild;
    public Image ZugBild;
    public Image Straße;
    public  Image Gleis;

    private boolean isJumping = false;
    private int jumpProgress = 0;
    private final int JUMP_DURATION = 40; // frames
    private int startX, startY;
    private int targetX, targetY;

    public Main()
    {
        setFocusable(true);

        try {
            Frosch = ImageIO.read(new File("Frog(Normal).png"));
            Frosche = ImageIO.read(new File ("Frog(Springen).png"));
            Gras = ImageIO.read(new File("Gras(normal).png"));
            AutoBild = ImageIO.read(new File("Polizeiauto(normal).png"));
            ZugBild = ImageIO.read(new File("Zug(normal).png"));
            Straße = ImageIO.read(new File("Straße(normal).png"));
            Gleis = ImageIO.read(new File("Gleis(normal).png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        addKeyListener(new java.awt.event.KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {   if (e.getKeyCode() == KeyEvent.VK_A && !isJumping) {
                isJumping = true;
                jumpProgress = 0;

                startX = player.x;
                startY = player.y;

                targetX = player.x - TILE;
                targetY = player.y;
            }
                if (e.getKeyCode() == KeyEvent.VK_W && !isJumping) {
                    isJumping = true;
                    jumpProgress = 0;

                    startX = player.x;
                    startY = player.y;

                    targetX = player.x;
                    targetY = player.y;
                    moveUpRequested=true;
                }
//                if (e.getKeyCode() == KeyEvent.VK_S && !isJumping) {
//                    isJumping = true;
//                    jumpProgress = 0;
//
//                    startX = player.x;
//                    startY = player.y;
//
//                    targetX = player.x;
//                    targetY = player.y + TILE;
//                }
                if (e.getKeyCode() == KeyEvent.VK_D && !isJumping) {
                    isJumping = true;
                    jumpProgress = 0;

                    startX = player.x;
                    startY = player.y;

                    targetX = player.x + TILE;
                    targetY = player.y;
                }
            }
        });
    }

    public void update() {

        if (isJumping) {
            jumpProgress++;

            double t = (double) jumpProgress / JUMP_DURATION;

            if (t >= 1.0) {
                player.y = targetY;
                isJumping = false;
            } else {
                player.x = (int)(startX + (targetX - startX) * t);
                player.y = (int)(startY + (targetY - startY) * t);
            }
        }

        if(player.CollisionCheck()==true)
        {
            GameOver();
        }

        if (moveUpRequested) {
            world.LaneGen();
            moveUpRequested = false;
        }

        player.hitbox.x = player.x;
        player.hitbox.y = player.y;
        for(int i=0;i<world.Lane.length;i++)
        {
            if(world.Lane[i].type==1&&world.Lane[i].Autos!=null)
            {
                for (int j = 0; j < world.Lane[i].Autos.length; j++)
                {
                    world.Lane[i].Autos[j].bewegen();
                    if(world.Lane[i].Autos[j].GegnerBox.x > 1100)
                    {
                        world.Lane[i].Autos[j].GegnerBox.x = -100;
                    }
                    world.Lane[i].Autos[j].Xposition=world.Lane[i].Autos[j].GegnerBox.x;
                    world.Lane[i].Autos[j].Yposition=world.Lane[i].Autos[j].GegnerBox.y=world.Lane[i].LaneBox.y;

                }
            }
            if(world.Lane[i].type==2 && world.Lane[i].Zuge!=null)
            {
                for(int j=0;j<world.Lane[i].Zuge.length;j++)
                {
                    if (world.Lane[i].Zuge[j] == null) continue;
                    world.Lane[i].Zuge[j].bewegen();
                    world.Lane[i].Zuge[j].ZugBox.x = world.Lane[i].Zuge[j].XPosition;
                    world.Lane[i].Zuge[j].ZugBox.y = world.Lane[i].LaneBox.y;
                }
            }

        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        for (int i = 0; i < world.Lane.length; i++) {
            world.Lane[i].draw(g);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + player.score, 10, 30);

        g.setColor(Color.BLUE);

        Image currentSprite = isJumping ? Frosche : Frosch;

        if(currentSprite != null)
        {
            g.drawImage(currentSprite, player.x, player.y, Player.SIZE, Player.SIZE, this);
        }

        for(int i=0; i<world.Lane.length; i++)
        {
            if(world.Lane[i].type == 1&&world.Lane[i].Autos!=null) {
                for (int j = 0; j < world.Lane[i].Autos.length; j++) {
                    if(world.Lane[i].Autos[j].GegnerBox!=null && AutoBild != null) {
                        g.drawImage( AutoBild, world.Lane[i].Autos[j].GegnerBox.x, world.Lane[i].Autos[j].GegnerBox.y, world.Lane[i].Autos[j].GegnerBox.width, world.Lane[i].Autos[j].GegnerBox.height, this);
                    }
                }
            }
            if(world.Lane[i].type == 2&&world.Lane[i].Zuge!=null) {
                for(int l =0; l<world.Lane[i].Zuge.length; l++) {
                    if(world.Lane[i].Zuge[l].ZugBox != null && ZugBild != null) {
                        g.drawImage(ZugBild, world.Lane[i].Zuge[l].ZugBox.x, world.Lane[i].Zuge[l].ZugBox.y, world.Lane[i].Zuge[l].ZugBox.width, world.Lane[i].Zuge[l].ZugBox.height, this);
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        Main.world.World();
        while (true) {

            update();
            repaint();

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void GameOver()
    {
        world=new World();
        world.World();
        player = new Player(400, 500);
        Main.player.x=WINDOW_WIDTH/2;
        Main.player.y=WINDOW_HEIGHT-Player.hitbox.height;
    }
    public static void main(String[] args) {
        Main.world = new World();
        world.World();
        Main.player = new Player(400, 500);
        Main.player.x=WINDOW_WIDTH/2;
        Main.player.y=WINDOW_HEIGHT-Player.hitbox.height;
        JFrame window = new JFrame();


        Main game = new Main();

        window.add(game);

        window.setSize(WINDOW_WIDTH-6, WINDOW_HEIGHT + 29);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        game.requestFocusInWindow();

        new Thread(game).start();
    }
}