package sumatra;


/**
 * Egy sarkkutató játékost reprezentáló osztály.
 */
public class Researcher extends Player{

    /**
     * A sarkkutató játékos konstruktora, meg kell adni mezőt, ahol kezdetben van a játékos.
     * @param startTile a mező melyen a játékos kezdetben van.
     */
    public Researcher(Tile startTile, int pindex){
        super(startTile, pindex);
        type = "Researcher";
        health = 5;
    }
    /**
     * A sarkkutató játékos felfedi egy mezőröl, hogy hányan férnek el rajta.
     * @param target a mező, amiről felfedi, hogy hányan férnek el.
     */
    @Override
    public void useAbility(Tile target){        
        if( mana > 0 && ( tile.isNeighbor(target) || target == this.tile ) ){
            target.revealCapacity();
            decreaseMana();
        }
    }
}