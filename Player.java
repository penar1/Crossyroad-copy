import java.awt.*;
public class Player {

    static final int SIZE = 16 * Main.FACTOR; // 64
    int y;
    int x;
    int speed;
    static Rectangle hitbox;
    int score = 0;

    public Player(int x, int y) {
        speed = Main.TILE; // 64 — movement step now matches grid unit, see note below
        hitbox = new Rectangle(x, y, SIZE, SIZE);
    }
    public boolean CollisionCheck()
    {
        for(int i=0; i<Main.world.Lane.length;i++)
        {
            if(Main.world.Lane[i].type==2) {
                if(Main.world.Lane[i].Zuge!=null){
                    for (int j = 0; j < Main.world.Lane[i].Zuge.length; j++) {
                        if (Main.world.Lane[i].Zuge[j] != null && Main.world.Lane[i].Zuge[j].ZugBox != null) {
                            if (hitbox.intersects(Main.world.Lane[i].Zuge[j].ZugBox)) {

                                return true;
                            }
                        }
                    }
                }
            }
            if(Main.world.Lane[i].type==1) {
                if(Main.world.Lane[i].Autos!= null){
                    for (int j = 0; j < Main.world.Lane[i].Autos.length; j++) {
                        if (Main.world.Lane[i].Autos[j] != null && Main.world.Lane[i].Autos[j].GegnerBox != null) {
                            if (hitbox.intersects(Main.world.Lane[i].Autos[j].GegnerBox)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}
