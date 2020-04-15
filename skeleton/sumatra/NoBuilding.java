package sumatra;

import java.util.ArrayList;

/**
 * Építmény leszármazott, azt szimbolizálja, hogy az adott mezőn nincs építmény.
 */
public class NoBuilding extends Building {

    /**
     * Szkeleton konstruktor, meg lehet adni neki az objektum nevét
     * @param objName Az objektum, mint változó neve
     */
    public NoBuilding(String objName) {
        super(objName);
    }

    /**
     * A birtokos mezőt érintő hóvihar bekövetkezésekor fut le. A paraméterként kapott játékosok
     * testhőmérsékletét egyel csökkenti.
     * @param ps A Buildinget tartalmazó Tile-on álló játékosok tömbje.
     */
    @Override
    public void onStorm(ArrayList<Player> ps) {
        Skeleton.printLine(objName, "onStorm()");
        for (Player p : ps) {
            p.damage(1);
        }
        Skeleton.returned();
    }
}
