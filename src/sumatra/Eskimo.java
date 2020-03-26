package sumatra;

/**
 * Egy eszkimó játékost reprezentáló osztály.
 */
public class Eskimo extends Player {

    /**
     * Az eszkimó játékos konstruktora, meg kell adni mezőt, ahol kezdetben van a játékos.
     * @param starTile a mező melyen a játékos kezdetben van.
     */
    public Eskimo(Tile startTile, String objName){
        super(startTile, objName);
    }
    /**
     * Az eszkimó játékos épít egy iglut a mezőjére
     * @param target a mező, amire épül az iglu.
     */
    @Override
    public void useAbility(Tile target){
        boolean hasMana = true; // opt van még munkaegysége
        if( hasMana ){
            target.buildIgloo();
        }
    }
}