package sumatra;

/**
 * Kötél leszármazott, azt reprezentálja, hogy egy játékosnak nincsen kötele.
 */
public class NoRope extends Rope {

    /**
     * Szkeleton konstruktor, meg lehet adni neki az objektum nevét
     * @param objName Az objektum, mint változó neve
     */
    public NoRope(String objName) {
        super(objName);
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
        Skeleton.printLine(objName, "save()");
        Skeleton.returned();
        return false;
    }

}
