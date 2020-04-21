package sumatra;

/**
 * Az osztály segítségével egy sátor helyezhető el a mezőn
 */
public class TentEquipment extends UsableItem {

    /**
     * Default ctor
     * @param objName a tárgy neve
     */
    public TentEquipment(String objName) {
        super(objName);
    }

    public TentEquipment() { super("tentequipment"); }

    @Override
    public void use(Tile t) {
        t.setBuilding(new Tent("tent"));

    }
}
