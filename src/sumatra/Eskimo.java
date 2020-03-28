package sumatra;

/**
 * Egy eszkimó játékost reprezentáló osztály.
 */
public class Eskimo extends Player {

    /**
     * Az eszkimó játékos konstruktora, meg kell adni mezőt, ahol kezdetben van a játékos.
     * @param starTile a mező melyen a játékos kezdetben van.
     */
    public Eskimo(Tile startTile, String pobjName){
        super(startTile, pobjName);
    }
    /**
     * Az eszkimó játékos épít egy iglut a mezőjére
     * @param target a mező, amire épül az iglu.
     */
    @Override
    public void useAbility(Tile target){
        Skeleton.printLine(this.objName, "useAbility()");
        
        boolean hasMana = Skeleton.askQuestion("Van-e még munkaegysége?");
        if( hasMana ){
            target.buildIgloo();
        }
        Skeleton.returned();
    }
}