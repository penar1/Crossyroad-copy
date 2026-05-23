import java.awt.*;

public class Auto extends Gegner{

    int Geschwindigkeit=50;

    Rectangle Auto;

    public Auto()
    {
        Rectangle Auto = new Rectangle( Xposition,  Yposition, 120, 90);

    }

    @Override
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
