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
}
