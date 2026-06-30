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

    private Image Frosch;
    private Image Frosche;
    public Image Gras;
    public Image AutoBild;
    public Image ZugBild;

    private boolean isJumping = false;
    private int jumpProgress = 0;
    private final int JUMP_DURATION = 15; // frames
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

                targetX = player.x - 120;
                targetY = player.y;
            }
                if (e.getKeyCode() == KeyEvent.VK_W && !isJumping) {
                    isJumping = true;
                    jumpProgress = 0;

                    startX = player.x;
                    startY = player.y;

                    targetX = player.x;
                    targetY = player.y - 120;
                }
                if (e.getKeyCode() == KeyEvent.VK_S && !isJumping) {
                    isJumping = true;
                    jumpProgress = 0;

                    startX = player.x;
                    startY = player.y;

                    targetX = player.x;
                    targetY = player.y + 120;
                }
                if (e.getKeyCode() == KeyEvent.VK_D && !isJumping) {
                    isJumping = true;
                    jumpProgress = 0;

                    startX = player.x;
                    startY = player.y;

                    targetX = player.x + 120;
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


        player.hitbox.x = player.x;
        player.hitbox.y = player.y;
        for(int i=0;i<world.Lane.length;i++)
        {
            if(world.Lane[i].type==1)
            {
                for (int j = 0; j < world.Lane[i].Autos.length; j++)
                {
                    world.Lane[i].Autos[j].bewegen();
                    if(world.Lane[i].Autos[j].GegnerBox.x > 900)
                    {
                        world.Lane[i].Autos[j].GegnerBox.x = -100;
                    }
                    world.Lane[i].Autos[j].Xposition=world.Lane[i].Autos[j].GegnerBox.x;
                    world.Lane[i].Autos[j].Yposition=world.Lane[i].Autos[j].GegnerBox.y;

                }
            }
            if(world.Lane[i].type==2)
            {
                for(int j=0;j<world.Lane[i].Zuge.length;j++)
                {
                    world.Lane[i].Zuge[j].ZugBox.x = world.Lane[i].Zuge[j].XPosition;
                    world.Lane[i].Zuge[j].ZugBox.y = world.Lane[i].LaneBox.y;
                    System.out.println(world.Lane[i].Zuge[j].XPosition);
                    world.Lane[i].Zuge[j].bewegen();
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
            g.drawImage(currentSprite, player.x, player.y, 64, 64, this);
        }

        for(int i=0; i<world.Lane.length; i++)
        {
            if(world.Lane[i].type == 1) {
                for (int j = 0; j < world.Lane[i].Autos.length; j++) {
                    if(world.Lane[i].Autos[j].GegnerBox!=null && AutoBild != null) {
                        g.drawImage( AutoBild, world.Lane[i].Autos[j].GegnerBox.x, world.Lane[i].Autos[j].GegnerBox.y, world.Lane[i].Autos[j].GegnerBox.width, world.Lane[i].Autos[j].GegnerBox.height, this);
                    }
                }
            }
            if(world.Lane[i].type == 2) {
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
                Thread.sleep(16);
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
    }
    public static void main(String[] args) {
        Main.world = new World();
        world.World();
        Main.player = new Player(400, 500);
        JFrame window = new JFrame();


        Main game = new Main();

        window.add(game);

        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        game.requestFocusInWindow();

        new Thread(game).start();
    }
}