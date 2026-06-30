import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Lanes {
    //variable um die verschiedenen bodenarten zu unterscheiden
    int type;
    //anzahl züge
    Image Gras;

    public Auto[] Autos;
    public Zug[] Zuge;
    Rectangle LaneBox;

    public Lanes()
    {
        settype((int) (Math.random() * 3));

        try {
            Gras = ImageIO.read(new File("Gras(normal).png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void Lane(int x, int y)
    {
        LaneBox =  new Rectangle(x, y, 800, 600/5);
    }

    public void draw(Graphics g)
    {
        if (type == 0) {
            if (Gras != null) {
                g.drawImage(Gras, LaneBox.x, LaneBox.y, LaneBox.width, LaneBox.height/2, null);
                g.drawImage(Gras, LaneBox.x, LaneBox.y+ LaneBox.height/2, LaneBox.width, LaneBox.height/2, null);
            }
        }
        else if (type == 1) {
            g.setColor(Color.GRAY);
            g.fillRect(LaneBox.x, LaneBox.y, LaneBox.width, LaneBox.height);
        }
        else if (type == 2) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(LaneBox.x, LaneBox.y, LaneBox.width, LaneBox.height);
        }

        g.setColor(Color.BLACK);
        g.drawRect(LaneBox.x, LaneBox.y, LaneBox.width, LaneBox.height);
    }

        //random bodenart
    public int settype(int type) 
    {
        this.type = type;
        return type;
    }
    
    //generation der gegner
    public void GegnerGen()
    {
        switch(type)
            {
                    //keine gegner, weil der boden gras ist
                case 0:
                    break;
                    //eine Straße hat auto
                    //diese werden in einem array gespeichert
                case 1:
                    Autos = new Auto[3];
                    for(int i =0; i<Autos.length; i++)
                        {
                            Autos[i] = new Auto(-i* (int)(Math.random()*900)+100, LaneBox.y);


                        }
                    break;
                case 2:
                    Zuge = new Zug[5];
                    for(int k=0; k<Zuge.length; k++)
                        {
                            Zuge[k] = new Zug();
                            Zuge[k].Yposition = LaneBox.y;
                        }
                    break;
            }
    }

    //testfunktion um zu testen ob die zufallsgeneration funktioniert
    public void typer()
        {
        switch(type)
        {
            case 0:
                System.out.print("gras");
                        break;
            case 1:
                System.out.print("straße");
                        break;
            case 2:
                System.out.print("gleise");
                        break;
        }

    }
}
