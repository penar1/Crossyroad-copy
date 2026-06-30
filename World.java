public class World {

  public Lanes[] Lane;
  public int[] Lanetype;
public void World() {
    Lane = new Lanes[5];
    for (int i = 0; i < Lane.length; i++) {
    Lane[i] = new Lanes();
    Lane[i].Lane(0, i * 120);
    Lane[i].GegnerGen();
    }
}
    public void LaneGen()
    {

        for (int i = 0; i < Lane.length; i++)
        {
            Lane[i].LaneBox.y += 120;
        }


        for (int i = 0; i < Lane.length; i++)
        {
            if (Lane[i].LaneBox.y >= 600)
            {

                Lane[i] = new Lanes();
                Lane[i].Lane(0, 0);
                Lane[i].GegnerGen();
            }
        }
    }

}
