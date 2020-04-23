package sumatra;

/**
 * Az osztály segítségével egy sátor helyezhető el a mezőn
 */
public class TentEquipment extends UsableItem {
    public TentEquipment() {
        itemtype = "tentequipment";
    }

    /**
     * Lerak egy Tent objektumot a paraméterül kapott mezőn
     * @param t A mező, amin az eszközt birtokló játékos az eszközt használni kívánja.
     */
    @Override
    public void use(Tile t) {
        t.setBuilding(new Tent(t));
    }
}
