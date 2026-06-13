import java.awt.*;
public class Player {

    int x;
    int y;
    int speed;
    static Rectangle hitbox;
    int score=0;
    boolean Collision=false;

    public Player(int x, int y) {
        speed=60;
        hitbox = new Rectangle(x, y, 50, 50);
    }
    public boolean CollisionCheck()
    {
        for(int i=0; i<Main.world.Lane.length;i++)
        {
            if(Main.world.Lane[i].type==2) {
                for(int j=0; j<Main.world.Lane[i].Zuge.length;j++) {
                    if(Main.world.Lane[i].Zuge[j]!=null&&Main.world.Lane[i].Zuge[j].ZugBox!=null) {
                        if (hitbox.intersects(Main.world.Lane[i].Zuge[j].ZugBox)) {
                            return true;
                        }
                    }
                }
            }
            if(Main.world.Lane[i].type==1) {
                for (int j = 0; j < Main.world.Lane[i].Autos.length; j++) {
                    if (Main.world.Lane[i].Autos[j] != null && Main.world.Lane[i].Autos[j].Auto != null) {
                        if (hitbox.intersects(Main.world.Lane[i].Autos[j].Auto)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public void bewegen() {
        score = score+1;
        hitbox.y -= 60;
    }
}
