package sumatra;

/**
 * Az osztály segítségével egy sátor helyezhető el a mezőn
 */
public class TentEquipment extends UsableItem {
    public TentEquipment() {
        type = "tentequipment";
    }

    @Override
    public void use(Tile t) {
        t.setBuilding(new Tent(t));
    }
}
