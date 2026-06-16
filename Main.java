import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Main extends JPanel implements Runnable {
    static World world;
    static Player player;
    static Zug zug;
    boolean leftPressed = false;
    boolean rightPressed = false;
    boolean upPressed = false;
    boolean downPressed = false;

    public Main()
    {
        setFocusable(true);

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
        g.fillRect(
                Player.hitbox.x,
                Player.hitbox.y,
                Player.hitbox.width,
                Player.hitbox.height
        );
        g.setColor(Color.cyan);
        for(int i=0; i<world.Lane.length; i++)
        {
            for(int j=0; j<world.Lane[i].Autos.length; j++)
            {
                g.drawRect(
                        world.Lane[i].Autos[j].Auto.x,
                        world.Lane[i].Autos[j].Auto.y,
                        world.Lane[i].Autos[j].Auto.width,
                        world.Lane[i].Autos[j].Auto.height
                );
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
        Main.player = new Player(370, 500);
        Main.zug = new Zug();
        JFrame window = new JFrame();


        Main game = new Main();
        world.World();
        window.add(game);

        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        game.requestFocusInWindow();

        new Thread(game).start();
    }
}