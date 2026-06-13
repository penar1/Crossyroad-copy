import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;

public class Main extends JPanel implements Runnable {
    static World world;
    static Player player;
    static Zug zug;

    public void update() {
        player.CollisionCheck();
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

        g.setColor(Color.BLUE);
        g.fillRect(
                Player.hitbox.x,
                Player.hitbox.y,
                Player.hitbox.width,
                Player.hitbox.height
        );
        for (int i = 0; i < world.Lane.length; i++) {
            world.Lane[i].draw(g);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + player.score, 10, 30);


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
        Main.player = new Player( 375, 500);
        Main.zug = new Zug();
        JFrame window = new JFrame();


        Main game = new Main();
        world.World();
        window.add(game);

        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        new Thread(game).start();
    }
}