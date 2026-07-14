import java.awt.*;


public class Auto extends Gegner{

    static final int WIDTH = 32 * Main.FACTOR;   // 128
    static final int HEIGHT = 16 * Main.FACTOR;  // 64, matches lane height exactly
    int Geschwindigkeit = 2;

    public Auto(int x, int y) {
        GegnerBox = new Rectangle(x, y, WIDTH, HEIGHT); // no Y_OFFSET needed now — same height as lane
    }
    public void bewegen() {
        GegnerBox.x += Geschwindigkeit;
    }
}
