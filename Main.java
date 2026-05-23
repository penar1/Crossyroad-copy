import javax.swing.*;
import java.awt.Graphics;

public class Main extends JPanel implements Runnable {



    public void update() {
        Player.Collisioncheck();
        for(int i=0;i<Autos.length;i++)
        {
            Autos[i].bewegen();
        }
        for(int i;i<zuge.length;i++)
        {
            zuge[i].bewegen();
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

        JFrame window = new JFrame();

        Main game = new Main();

        window.add(game);

        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        new Thread(game).start();
    }
}