package sumatra;

import java.io.OutputStream;

/**
 * Egy eszkimó játékost reprezentáló osztály.
 */
public class Eskimo extends Player {
    @Override
    public void printData(OutputStream stream) {
        // TODO Auto-generated method stub
        
    }

    /**
     * Az eszkimó játékos konstruktora, meg kell adni mezőt, ahol kezdetben van a játékos.
     * @param starTile a mező melyen a játékos kezdetben van.
     */
    public Eskimo(Tile startTile, int pindex){
        super(startTile, pindex);
    }
    /**
     * Az eszkimó játékos épít egy iglut a mezőjére
     * @param target a mező, amire épül az iglu.
     */
    @Override
    public void useAbility(Tile target){        
        if( mana > 0 ){
            target.buildIgloo();
            --mana;
        }
    }
}