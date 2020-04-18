package sumatra;

import java.util.ArrayList;

public class Tent extends Building {

    /**
     * Alap ctor, meg lehet adni neki az objektum nevét
     * @param objName Az objektum, mint változó neve
     */
    public Tent(String objName) {
        super(objName);
    }

    /**
     * A tile, sátor viselkedése vihar esetén.
     * @param ps A Buildinget tartalmazó Tile-on álló játékosok tömbje.
     */
    @Override
    public void onStorm(ArrayList<Player> ps) {

    }

    // TODO Legyen megsemmisítő függvénye, mondjuk destroy()
    // - Attila
}
