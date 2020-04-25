package sumatra;

/**
 * Az osztály segítségével egy sátor helyezhető el a mezőn
 */
public class TentEquipment extends UsableItem {
    private Player owner;
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
        owner.removeUsableItem(this);
        System.out.println("    > Tent placed on current tile");
    }
    /**
     * Az eszközt hozzárendeli a paraméterként kapott játékoshoz.
     * @param p azon játékos, aki a tárgyat birtokolni fogja, vagy a tárgy valamilyen akciót hajt rajta végre
     */
    @Override
    public void giveToPlayer(Player p) {
        super.giveToPlayer(p);
        owner = p;
    }
}
