package sumatra;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Instabil táblát reprezentáló osztály
 */
public class UnstableTile extends Tile {

    /**
     * Elfogadja a táblára lépő élőlényt
     * @param c A táblára lépő élőlény
     */
    @Override
    public void accept(Creature c) {
        creatures.add(c);
        if (creatures.size() >= capacity) {
            flip();
        }
    }

    /**
     * Instabil tábla konstruktora
     * @param snowAmount A jégtáblán lévő hó mélysége
     */
    public UnstableTile(int snowAmount, int cap) {
        super(snowAmount);
        capacity = cap;
        type = "unstable";
    }

    /**
     * Az összes rajta álló élőlényt a vízbe dobja
     */
    public void flip() {
        System.out.println("> It flips over, you fall into water!");
        for (Creature c : creatures)
            c.fallInWater();
    }

    /**
     * Kiírja a tile adatait felhasználóbarát módon - a tiles parancs kimenetéhez kell.
     */
    public void listGameInfo() {
        int idx = World.getInstance().getTileIndex(this);
        String type = (is_capacity_known) ? "U (" + capacity + ")" : "?";
        String visibleitem = (snowlayers == 0 && item != null) ? ", visible item" : "";
        System.out.println(idx + ": " + type + visibleitem);
    }
}
