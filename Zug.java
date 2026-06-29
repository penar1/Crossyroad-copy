import java.awt.*;

public class Zug extends Gegner{

Rectangle ZugBox;
int Geschwindigkeit=80;
int XPosition=800;
int YPosition;
public Zug()
{
    ZugBox= new Rectangle(XPosition, Yposition, 120, 100);
    long start = System.currentTimeMillis();

    while(System.currentTimeMillis() - start < ((int) Math.random()*4000) +1)
    {

    }
    while(XPosition>-120) {
        bewegen();
    }
}
}
