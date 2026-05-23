public class Zug extends Gegner{

Rectangle ZugBox;
int Geschwindigkeit=80;
int XPosition=800;
public void Zug()
{

    ZugBox= new Rectangle(XPosition, YPosition, 120, 100)
    try {
    Thread.sleep(((int)(Math.random)*10000)+4000);
    } 
    catch (InterruptedException e) {
        e.printStackTrace();
    }
}
}
