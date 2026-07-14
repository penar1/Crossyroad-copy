import java.awt.*;

public class Zug extends Gegner {

    static final int WIDTH = 75 * Main.FACTOR;   // 300
    static final int HEIGHT = 16 * Main.FACTOR;  // 64, matches lane height exactly
    Rectangle ZugBox;
    int Geschwindigkeit = 7;
    int XPosition = -WIDTH;
    int YPosition;
    int startXPosition;
    int targetXPosition = 900;
    private int waitFrames;
    private int waitElapsed = 0;
    private boolean moving = false;

    public Zug() {
        ZugBox = new Rectangle(XPosition, Yposition, WIDTH, HEIGHT);
        double waitSeconds = 3 + Math.random() * 5;
        waitFrames = (int) (waitSeconds * 60);
        startXPosition = XPosition;
    }

    @Override
    public void bewegen() {
        if (!moving) {
            waitElapsed++;
            if (waitElapsed >= waitFrames) {
                moving = true;
            }
            return;
        }

        XPosition += Geschwindigkeit;

        if (XPosition >= targetXPosition) {
            XPosition = startXPosition;
            moving = false;
            waitElapsed = 0;
            waitFrames = (int) ((1 + Math.random() * 3) * 60);
        }
    }
}
