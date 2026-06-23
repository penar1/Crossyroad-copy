import java.awt.*;

public class Gegner {
  int Xposition;
  int Yposition;
  int Geschwindigkeit;
  Rectangle GegnerBox;

  public void bewegen() 
  {
        Xposition = Xposition - Geschwindigkeit;
    }
  
}
