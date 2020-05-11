package sumatra;

/**
 * Kötél leszármazott, azt reprezentálja, hogy egy játékosnak nincsen kötele.
 */
public class NoRope extends Rope {
    /**
     * Default ctor
     */
    public NoRope() {
        type = "none";
    }
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
}
