public class World {

  Lanes[] Lane = new Lanes[5];

public void World() {
  for (int i = 0; i < Lane.length; i++) {
    Lane[i] = new Lanes();
    Lane[i].Lanes(0, 120 + i * 120);
    Lane[i].settype((int) (Math.random() * 3));
    Lane[i].GegnerGen();
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
