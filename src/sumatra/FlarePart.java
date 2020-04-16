package sumatra;

/**
 * 
 */
public abstract class FlarePart extends Item {
    /**
     * default ctor
     * @param objName az eszköz neve
     */
    public FlarePart(String objName) {
        super(objName);
    }

    /** Beregisztrálja a felvett rakétarészt
     * @param p a rakétarészt felvevő játékos
     */
    @Override
    public void giveToPlayer(Player p) {
        Skeleton.printLine(objName, "giveToPlayer()");
        World.getInstance().registerFlarePart(this);
        Skeleton.returned();
    }
}