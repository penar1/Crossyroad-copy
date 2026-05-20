public class World {

  Lanes[] Lane = new Lanes[5];

public void World()
{
for(int i=0; i<Lane.length; i++)
  {
    Lane[i] = new Lanes();
    Lane[i].Lanes(0, 120+i*120);
    Lane[i].settype((int)(Math.random()*3));
  }
}  

}
