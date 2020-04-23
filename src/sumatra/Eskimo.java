package sumatra;

import java.io.OutputStream;

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
    }
    /**
     * Az eszkimó játékos épít egy iglut a mezőjére
     * @param target a mező, amire épül az iglu.
     */
    @Override
    public void useAbility(Tile target){        
        if( mana > 0 ){
            target.setBuilding(new Igloo());
            --mana;
        }
    }
}