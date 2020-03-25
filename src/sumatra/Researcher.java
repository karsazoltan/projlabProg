package sumatra;

/**
 * Egy sarkkutató játékost reprezentáló osztály.
 */
public class Researcher extends Player{

    /**
     * A sarkkutató játékos felfedi egy mezőröl, hogy hányan férnek el rajta.
     * @param target a mező, amiről felfedi, hogy hányan férnek el.
     */
    @Override
    public void useAbility(Tile target){
        boolean isTargetValid = true; // saját vagy szomszédos target
        boolean hasMana = true; // opt van még munkaegysége
        if( hasMana && isTargetValid )
            target.revealCapacity();
    }
}