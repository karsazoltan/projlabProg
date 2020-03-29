package sumatra;

/**
 * 
 */
public class Food extends Item {
    /**
     * Default ctor
     */
    public Food(String objName) {
        super(objName);
    }
    /** Ezen Item hatását rögtön kifejti, és elpusztul: Növeli a játékos életerejét 1-el
     * @param p a tárgyat felveő játékos
     */
    public void giveToPlayer(Player p) {
        Skeleton.printLine(objName, "giveToPlayer");
        p.heal(1);
        Skeleton.returned();
    }
}