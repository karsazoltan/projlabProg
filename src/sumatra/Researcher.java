package sumatra;

/**
 * Egy sarkkutató játékost reprezentáló osztály.
 */
public class Researcher extends Player{

    /**
     * A sarkkutató játékos konstruktora, meg kell adni mezőt, ahol kezdetben van a játékos.
     * @param starTile a mező melyen a játékos kezdetben van.
     */
    public Researcher(Tile startTile){
        super(startTile);
    }
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