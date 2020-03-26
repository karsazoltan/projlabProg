package sumatra;

import java.util.ArrayList;

public class UnstableTile extends Tile {
    public void flip() {
        ArrayList<Player> players = getPlayers();
        for (Player p : players)
            p.fallInWater();
    }
}
