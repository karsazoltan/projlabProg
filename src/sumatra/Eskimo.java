package sumatra;


/**
 * Egy eszkimó játékost reprezentáló osztály.
 */
public class Eskimo extends Player {

    /**
     * Az eszkimó játékos konstruktora, meg kell adni mezőt, ahol kezdetben van a játékos.
     * @param startTile a mező melyen a játékos kezdetben van.
     */
    public Eskimo(Tile startTile, int pindex){
        super(startTile, pindex);
        type = "Eskimo";
        health = 5;
    }
    /**
     * Az eszkimó játékos épít egy iglut a mezőjére
     * @param target a mező, amire épül az iglu.
     */
    @Override
    public void useAbility(Tile target){        
        if( mana > 0 ){
            target.setBuilding(new Igloo());
            decreaseMana();
            System.out.println("    > Igloo built on current tile");
        }
    }
}