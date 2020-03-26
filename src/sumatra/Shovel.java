package sumatra;

/**
 * Ásó manuálisan használható eszköz, használatával egyszerre két réteg hó is letakarítható egy mezőről.
 */
public class Shovel extends UsableItem {

    public Shovel(String objName) {
        super(objName);
    }

    /**
     * Ásó használata függvény. A paraméterként kapott mezőről leás két réteg havat.
     * @param t A mező, amin az eszközt birtokló játékos az eszközt használni kívánja.
     */
    @Override
    public void use(Tile t) {
        Skeleton.printLine(objName, "use()");
        t.removeSnow(2);
        Skeleton.returned();
    }
}