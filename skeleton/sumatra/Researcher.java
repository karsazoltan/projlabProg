package sumatra;

/**
 * Egy sarkkutató játékost reprezentáló osztály.
 */
public class Researcher extends Player{

    /**
     * A sarkkutató játékos konstruktora, meg kell adni mezőt, ahol kezdetben van a játékos.
     * @param startTile a mező melyen a játékos kezdetben van.
     */
    public Researcher(Tile startTile, String pobjName){
        super(startTile, pobjName);
    }
    /**
     * A sarkkutató játékos felfedi egy mezőröl, hogy hányan férnek el rajta.
     * @param target a mező, amiről felfedi, hogy hányan férnek el.
     */
    @Override
    public void useAbility(Tile target){
        Skeleton.printLine(this.objName, "useAbility()");
        
        boolean isTargetValid = Skeleton.askQuestion("Saját vagy szomszédos mező a cél?");
        boolean hasMana = Skeleton.askQuestion("Van-e még munkaegysége?");
        if( hasMana && isTargetValid )
            target.revealCapacity();

        Skeleton.returned();
    }
}