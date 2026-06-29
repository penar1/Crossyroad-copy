import java.awt.*;


public class Auto extends Gegner{

    int Geschwindigkeit=5;

    public Auto(int x, int y)
    {
        GegnerBox = new Rectangle( x,  y, 200, 100);

    }


    public void bewegen() {
        GegnerBox.x += Geschwindigkeit;

    }
}
