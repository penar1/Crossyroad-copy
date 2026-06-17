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
    public Image Gras;
    boolean leftPressed = false;
    boolean rightPressed = false;
    boolean upPressed = false;
    boolean downPressed = false;

    public Main()
    {
        setFocusable(true);

        try {
            Frosch = ImageIO.read(new File("Frog(Normal).png"));
            Gras = ImageIO.read(new File("Gras(normal).png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        addKeyListener(new java.awt.event.KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_A) player.x -= 120;
                if (e.getKeyCode() == KeyEvent.VK_W) player.y -= 120;
                if (e.getKeyCode() == KeyEvent.VK_S) player.y += 120;
                if (e.getKeyCode() == KeyEvent.VK_D) player.x += 120;
            }
        });
    }

    public void update() {
        player.CollisionCheck();


        player.hitbox.x = player.x;
        player.hitbox.y = player.y;
        for(int i=0;i<world.Lane.length;i++)
        {
            if(world.Lane[i].type==1)
            {
                for (int j = 0; j < world.Lane[i].Autos.length; j++)
                {
                    world.Lane[i].Autos[j].bewegen();
                }
            }
            if(world.Lane[i].type==2)
            {
                for(int j=0;j<world.Lane[i].Zuge.length;j++)
                {
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
        if(Frosch != null)
        {
            g.drawImage(Frosch, player.x, player.y, 64, 64, this);
        }
//        g.fillRect(
//                Player.hitbox.x,
//                Player.hitbox.y,
//                Player.hitbox.width,
//                Player.hitbox.height
//        );
        g.setColor(Color.cyan);
        for(int i=0; i<world.Lane.length; i++)
        {
            if(world.Lane[i].type == 1) {
                for (int j = 0; j < world.Lane[i].Autos.length; j++) {
                    if(world.Lane[i].Autos[j].AutoBox!=null) {
                        g.fillRect(
                                world.Lane[i].Autos[j].AutoBox.x,
                                world.Lane[i].Autos[j].AutoBox.y,
                                world.Lane[i].Autos[j].AutoBox.width,
                                world.Lane[i].Autos[j].AutoBox.height
                        );
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

    public static void main(String[] args) {
        Main.world = new World();
        world.World();
        Main.player = new Player(370, 500);
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