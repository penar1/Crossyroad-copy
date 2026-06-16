public class World {

  public Lanes[] Lane;

public void World() {
    Lane = new Lanes[5];
    for (int i = 0; i < Lane.length; i++) {
    Lane[i] = new Lanes();
    if(i!=0)
    {
        Lane[i].Lanes(0, 120 + i * 120);
    }
    else
    {
        Lane[i].Lanes(0,0);
    }
    }
}
  public void LaneGen()
  {
    for(int i = Lane.length - 1; i > 0; i--) {
      Lane[i] = Lane[i - 1];
      Lane[0] = new Lanes();
      Lane[0].Lanes(0, 120);
    }

  }


}
