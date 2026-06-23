import java.awt.*;


public class Auto extends Gegner{

    int Geschwindigkeit=50;

    Rectangle AutoBox;

    public Auto()
    {
        AutoBox = new Rectangle( 0,  Yposition, 120, 90);

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
