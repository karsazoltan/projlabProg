package sumatra;

import java.io.OutputStream;

/**
 * Ásó manuálisan használható eszköz, használatával egyszerre két réteg hó is letakarítható egy mezőről.
 */
public class Shovel extends UsableItem {
    public Shovel() {
        type = "shovel";
    }

    /**
     * Ásó használata függvény. A paraméterként kapott mezőről leás két réteg havat.
     * @param t A mező, amin az eszközt birtokló játékos az eszközt használni kívánja.
     */
    @Override
    public void use(Tile t) {
        t.removeSnow(2);
    }
}
