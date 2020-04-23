package sumatra;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Kötél leszármazott, azt reprezentálja, hogy egy játékosnak nincsen kötele.
 */
public class NoRope extends Rope {
    /**
     * Játékosmentő függvény, a NoRope esetében viszont nem történik semmi, mert ezzel a "kötéllel"
     * nem lehet játékost menteni.
     * @param p A kimentendő játékos.
     * @param t A kimentendő játékos mezője.
     * @param newTile A kötelet birtokló játékos mezője.
     */
    @Override
    public boolean save(Player p, Tile t, Tile newTile) {
        return false;
    }

    @Override
    public void printData(OutputStream stream) {
        PrintWriter pw = new PrintWriter(stream);
        pw.println("rope none");
    }
}
