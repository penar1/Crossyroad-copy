import java.awt.*;

public class Auto extends Gegner{

    int Geschwindigkeit=50;

    Rectangle AutoBox;

    public Auto()
    {
        Rectangle AutoBox = new Rectangle( Xposition,  Yposition, 120, 90);

    }


    public void bewegen(String Richtung) {
        if(Richtung == "Rechts")
        {
            Xposition += Geschwindigkeit;
        }
        if(Richtung == "Links")
        {
           Xposition -= Geschwindigkeit;
        }
    }
}
