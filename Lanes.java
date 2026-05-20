import java.awt.*;

public class Lanes {
    //variable um die verschiedenen bodenarten zu unterscheiden
    int type;
    //anzahl züge
    
    public Auto[] Autos = new Auto[3];
    public Zug[] Zuge = new Zug[5];
    static Rectangle g;

    public void Lanes(int x, int y)
    {
        g =  new Rectangle(x, y, 800, 600/5);
    }

    public void draw(Graphics g)
    {

        switch (type) {
            case 0:
                g.setColor(Color.GREEN);
                break;
            case 1:
                g.setColor(Color.GRAY);
                break;
            case 2:
                g.setColor(Color.DARK_GRAY);
                break;
        }
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
                    for(int i =0; i<Autos.length; i++)
                        {
                            Autos[i] = new Auto();
                        }
                    break;
                case 2:
                    for(int k=0; k<Zuge.length; k++)
                        {
                            Zuge[k] = new Zug();
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
