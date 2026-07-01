import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Lanes {
    //variable um die verschiedenen bodenarten zu unterscheiden
    int type;
    //anzahl züge
    Image Gras;
    Image Straße;
    Image Gleis;

    public Auto[] Autos;
    public Zug[] Zuge;
    Rectangle LaneBox;

    public Lanes()
    {
        settype((int) (Math.random() * 3));

        try {
            Gras = ImageIO.read(new File("Gras(normal).png"));
            Straße = ImageIO.read(new File("Straße(normal).png"));
            Gleis = ImageIO.read(new File("Gleis(normal).png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void Lane(int x, int y)
    {
        LaneBox = new Rectangle(x, y, 200 * Main.FACTOR, Main.TILE);
    }

    public void draw(Graphics g) {
        if (type == 0 && Gras != null) {
                g.drawImage(Gras, LaneBox.x, LaneBox.y, LaneBox.width, LaneBox.height, null);
            }
        else if (type == 1 && Straße != null) {
                g.drawImage(Straße, LaneBox.x, LaneBox.y, LaneBox.width, LaneBox.height, null);
            }
        else if (type == 2 && Gleis != null) {
                g.drawImage(Gleis, LaneBox.x, LaneBox.y, LaneBox.width, LaneBox.height, null);
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
                    Zuge = new Zug[1];
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
