import java.awt.*;
public class Player {

    int x;
    int y;
    int speed;
    static Rectangle hitbox;
    int score=0;
    boolean Collision=false;

    public Player(int x, int y) {
        hitbox = new Rectangle(x, y, 50, 50);
    }
    public boolean CollisionCheck()
    {
        if(hitbox.intersects(Zug.ZugBox))
        {
            boolean Collision=true;
        }
        if(hitbox.intersects(Auto.Rectangle))
        {
            boolean Collision=true;
        }
    }
    public void bewegen() {
        score = score+1;
        hitbox.y -= 60;
    }
}
