package sumatra;

import java.util.ArrayList;

/**
 * Építmény leszármazott, azt szimbolizálja, hogy az adott mezőn nincs építmény.
 */
public class NoBuilding extends Building {
    /**
     * A birtokos mezőt érintő hóvihar bekövetkezésekor fut le. A paraméterként kapott játékosok
     * testhőmérsékletét egyel csökkenti.
     * @param ps A Buildinget tartalmazó Tile-on álló játékosok tömbje.
     */
    @Override
    public void onStorm(ArrayList<Player> ps) {
        for (Player p : ps) {
            p.damage(1);
        }
    }
}
