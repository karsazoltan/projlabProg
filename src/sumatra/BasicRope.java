package sumatra;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Kötél leszármazott, azt reprezentálja, hogy egy játékosnak van kötele.
 */
public class BasicRope extends Rope {

    /**
     * Szkeleton konstruktor, meg lehet adni neki az objektum nevét
     * @param objName Az objektum, mint változó neve
     */
    public BasicRope(String objName) {
        super(objName);
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
    @Override
    public void printData(OutputStream stream) {
        PrintWriter pw = new PrintWriter(stream);
        pw.println("rope basicrope");
    }
}
