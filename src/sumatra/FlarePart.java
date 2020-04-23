package sumatra;

/**
 * 
 */
public abstract class FlarePart extends Item {
    /** Beregisztrálja a felvett rakétarészt
     * @param p a rakétarészt felvevő játékos
     */
    @Override
    public void giveToPlayer(Player p) {
        World.getInstance().registerFlarePart(this);
    }
}