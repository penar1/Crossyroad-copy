import javax.swing.*;
import java.awt.Graphics;

public class Main extends JPanel implements Runnable {
    static World world;
    Player player;

    public void update() {
        player.CollisionCheck();
        for(int i=0;i<world.Lane.length;i++)
        {
            
        
        for(int j=0;j<world.Lane[i].Autos.length;j++)
        {
            world.Lane[i].Autos[j].bewegen();
        }
       for(int j=0;j<world.Lane[i].Zuge.length;j++)
        {
            world.Lane[i].Zuge[j].bewegen();
        }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillRect(
                Player.hitbox.x,
                Player.hitbox.y,
                Player.hitbox.width,
                Player.hitbox.height
        );
    }

    @Override
    public void run() {

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
        World world = new World();
        Player player = new Player( 400, 60);
        JFrame window = new JFrame();

        Main game = new Main();

        window.add(game);

        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        new Thread(game).start();
    }
}