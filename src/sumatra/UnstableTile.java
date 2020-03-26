package sumatra;

import java.util.ArrayList;

/**
 * Instabil táblát reprezentáló osztály
 */
public class UnstableTile extends Tile {
    /**
     * Az összes rajta álló játékost a vízbe dobja
     */
    public void flip() {
        ArrayList<Player> players = getPlayers();
        for (Player p : players)
            p.fallInWater();
    }
}
