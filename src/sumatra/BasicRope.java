package sumatra;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Kötél leszármazott, azt reprezentálja, hogy egy játékosnak van kötele.
 */
public class BasicRope extends Rope {
    public BasicRope() {
        type = "basic";
    }

    /**
     * Játékosmentő függvény. Ebben az esetben a játékos tényleg kimentésre kerül, azaz a paraméterként
     * kapott játékos átkerül az eddigi t mezőjéről az új newTile mezőre, amin a kötél birtokosa áll.
     * @param p A kimentendő játékos.
     * @param t A kimentendő játékos mezője.
     * @param newTile A kötelet birtokló játékos mezője.
     */
    @Override
    public boolean save(Player p, Tile t, Tile newTile) {
        p.forceMove(newTile);
        return true;
    }
}
