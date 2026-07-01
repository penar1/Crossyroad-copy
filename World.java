public class World {
    public volatile Lanes[] Lane;   // volatile: ensures the EDT sees the latest published reference
    public int[] Lanetype;

    public void World() {
        Lanes[] newLanes = new Lanes[Main.LANE_COUNT];
        for (int i = 0; i < newLanes.length; i++) {
            newLanes[i] = new Lanes();
            newLanes[i].Lane(0, i * Main.TILE);
            newLanes[i].GegnerGen();
        }
        Lane = newLanes;
    }

    public void LaneGen() {
        Lanes[] currentLanes = Lane;
        for (int i = 0; i < currentLanes.length; i++) {
            currentLanes[i].LaneBox.y += Main.TILE;
        }
        for (int i = 0; i < currentLanes.length; i++) {
            if (currentLanes[i].LaneBox.y >= Main.WINDOW_HEIGHT) {
                Lanes fresh = new Lanes();
                fresh.Lane(0, 0);
                fresh.GegnerGen();
                currentLanes[i] = fresh;
            }
        }
        Main.player.score++;
    }
}