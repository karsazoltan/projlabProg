package sumatra;

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
        building.newCreature(c, creatures);
        creatures.add(c);
        if (creatures.size() > capacity) {
            flip();
        }
        updateViews();
    }

    /**
     * Instabil tábla konstruktora
     * @param snowAmount A jégtáblán lévő hó mélysége
     */
    public UnstableTile(int id, int snowAmount, int cap) {
        super(id, snowAmount);
        capacity = cap;
        type = "unstable";
    }

    /**
     * Az összes rajta álló élőlényt a vízbe dobja
     */
    public void flip() {
        System.out.println("> It flips over, you fall into water!");
        ArrayList<Creature> copy = new ArrayList<>(creatures);
        for (Creature c : copy)
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
