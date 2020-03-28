package sumatra;

import java.util.ArrayList;

/**
 * Instabil táblát reprezentáló osztály
 */
public class UnstableTile extends Tile {
    /**
     * Szkeleton konstruktor, meg lehet adni neki az objektum nevét
     * @param objName Az objektum, mint változó neve
     */
    public UnstableTile(String objName) {
        super(objName);
    }

    /**
     * Az összes rajta álló játékost a vízbe dobja
     */
    public void flip() {
        ArrayList<Player> players = getPlayers();
        for (Player p : players)
            p.fallInWater();
    }
}
