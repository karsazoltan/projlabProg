package sumatra;

/**
 * Ásó manuálisan használható eszköz, használatával egyszerre két réteg hó is letakarítható egy mezőről.
 */
public class BrokenShovel extends UsableItem {
    int use_num;
    public BrokenShovel(int maxuse) {
        super(null); // TODO Sorry, temp fix hogy nekem működjön ~ Attila
        use_num = maxuse;
    }
    public BrokenShovel(String objName) {
        this(3);
    }
    /**
     * Ásó használata függvény. A paraméterként kapott mezőről leás két réteg havat.
     * @param t A mező, amin az eszközt birtokló játékos az eszközt használni kívánja.
     */
    @Override
    public void use(Tile t) {
        Skeleton.printLine(objName, "use()");
        if(amortization())
            t.removeSnow(2);
        Skeleton.returned();
    }
    private boolean amortization() {
        use_num--;
        return use_num >= 0;
    }
}