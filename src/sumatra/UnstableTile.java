package sumatra;

import java.util.ArrayList;

public class UnstableTile extends Tile {
    public void flip() {
        ArrayList<Player> players = getPlayers();
        for (int i = 0; i < getPlayers().size(); i++)
            players.get(i).fallInWater();
    }
}
