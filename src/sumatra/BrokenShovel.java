package sumatra;

/**
 * Ásó manuálisan használható eszköz, használatával egyszerre két réteg hó is letakarítható egy mezőről.
 */
public class BrokenShovel extends UsableItem {
    int use_num;
    public BrokenShovel(int maxuse) {
        use_num = maxuse;
    }
    public BrokenShovel() {
        this(3);
    }
    /**
     * Ásó használata függvény. A paraméterként kapott mezőről leás két réteg havat.
     * @param t A mező, amin az eszközt birtokló játékos az eszközt használni kívánja.
     */
    @Override
    public void use(Tile t) {
        if(amortization())
            t.removeSnow(2);
    }
    private boolean amortization() {
        use_num--;
        return use_num >= 0;
    }
}