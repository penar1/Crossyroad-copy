import java.awt.*;
public class Player {

    int x;
    int y;
    int speed;
    static Rectangle hitbox;

    public Player(int x, int y) {
        hitbox = new Rectangle(x, y, 50, 50);
    }

    public void bewegen() {
       
        hitbox.y -= 60;
    }
}
