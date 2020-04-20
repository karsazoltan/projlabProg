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
        creatures.add(c);
        boolean enoughCapacity = Skeleton.askQuestion("Elbírja a jégtábla a játékosokat?");
        if (!enoughCapacity) {
            flip();
        }
    }

    /**
     * Szkeleton konstruktor, meg lehet adni neki az objektum nevét
     * @param objName Az objektum, mint változó neve
     */
    public UnstableTile(String objName) {
        super(objName);
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
}
