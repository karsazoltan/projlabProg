package sumatra;

/**
 * Kötél leszármazott, azt reprezentálja, hogy egy játékosnak van kötele.
 */
public class BasicRope extends Rope {

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
    public void save(Player p, Tile t, Tile newTile) {
        Skeleton.printLine(objName, "save()");
        p.move(newTile);
        Skeleton.returned();
    }
}
