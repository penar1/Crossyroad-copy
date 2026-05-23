import java.awt.*;
public class Player {

    int x;
    int y;
    int speed;
    static Rectangle hitbox;
    int score=0;

    public Player(int x, int y) {
        hitbox = new Rectangle(x, y, 50, 50);
    }

    public void bewegen() {
        score = score+1;
        hitbox.y -= 60;
    }
}
