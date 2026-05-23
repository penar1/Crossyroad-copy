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
        for(int i=0; i<Lane.length;i++)
        {
        for(int j=0; j<Lane[i].Zuge.length;j++)
        {
        if(hitbox.intersects(zuge[j].Zug.ZugBox))
        {
            return true;
        }
        }
        for(int j=0;j<Lane[i].Autos.length;j++)
        {
        if(hitbox.intersects(Autos[j].Auto.Auto))
        {
            return true;
        }
        }
        }
    }
    public void bewegen() {
        score = score+1;
        hitbox.y -= 60;
    }
}
