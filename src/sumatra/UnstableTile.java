package sumatra;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
        if (creatures.size() > capacity) {
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
    }

    /**
     * Az összes rajta álló élőlényt a vízbe dobja
     */
    public void flip() {
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

    public void printData(OutputStream os) {
        Writer writer = new OutputStreamWriter(os);
        writer.write(World.getInstance().getTileIndex(this)+" "+" Stable "+ snowlayers + " " + (is_capacity_known ? "y ":"n ") + building.printData(os) + " " + item.printData(os) + " " + capacity);
    }
}
