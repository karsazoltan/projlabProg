package sumatra;

import java.util.ArrayList;

/**
 * <p>Absztrakt osztály, a játéksokat reprezentálja, leszármazottjai a különböző játékos típusok.</p>
 * 
 * <p>Megjegyzés:
 * Az optos részek valószínüleg nem ide fognak kerülni, csak erre azután jöttem rá,
 * miután megcsináltam őket.</p>
 */
public abstract class Player {
    /**
     * A mező melyen a játékos jelenleg tartózkodik.
     */
    Tile tile;
    /**
     * A játékos búvárruhája (búvárruha vagy nem búvárruha).
     */ 
    DivingSuit divingSuit;
    /**
     * A játékos kötele (kötél vagy nem kötél).
     */ 
    Rope rope;
    /**
     * A játékos használható tárgyai.
     */ 
    ArrayList<UsableItem> useableItems;

    /**
     * A játékos konstruktora, meg kell adni mezőt, ahol kezdetben van a játékos.
     * @param starTile a mező melyen a játékos kezdetben van.
     */
    public Player(Tile startTile){
        tile = startTile;
        divingSuit = new NoDivingSuit();
        rope = new NoRope();
        useableItems = new ArrayList<UsableItem>();
    }

    /**
     * Visszaadja azt a mezőt, amin jelenleg van a játékos.
     * @return mező melyen jelenleg van a játékos.
     */ 
    public Tile getTile(){
        return tile;
    }

    /**
     * Absztrakt függvény, ezt implementálják a játékos típusok, hogy különböző képességeik legyenek.
     */ 
    abstract public void useAbility(Tile target);

    /**
     * A játékost egy új mezőre lépteti.
     * @param newTile a mező, ahova a játékos lép.
     */
    public void move(Tile newTile) {

        boolean hasMana = true; // opt van még munkaegysége

        if( hasMana ){
            tile.remove(this);
            newTile.accept(this);

            tile = newTile; // vagy Tile.accept-be Player.setTile(this); ???
        }
    }

    /**
     * Ás egyet a játékos, eltüntet egy réteg havat a mezőjéről.
     */ 
    public void dig() {
        boolean hasMana = true; // opt van még munkaegysége
        
        if( hasMana ){
            tile.removeSnow(1);
        }
    }


    /**
     * A játékos beleesik a vízbe.
     */ 
    public void fallInWater() {     
        divingSuit.fallInWater(this);
    }

    /**
     * A játékos használ egy tárgyat a táskájából.
     * @param index használandó tárgy indexe a játékos táskájában.
     * @param target mező amin felhasználja a játékos a tárgyat.
     */ 
    public void useItem(int index, Tile target) {
        boolean hasMana = true; // opt van még munkaegysége
        
        if( hasMana ){
            useableItems.get(index).use(target);
        }
    }

    /**
     * A játékos felvesz egy tárgyat a mezőjéről.
     */ 
    public void pickUpItem() {
        boolean hasMana = true; // opt van még munkaegysége
        
        if( hasMana ){
            tile.pickUpItem(this);
        }
    }

    /**
     * A játékos összerakja a flare-t, ha ez sikerül befejeződik a játék.
     */ 
    public void buildFlare() {
        World.getInstance().checkEndGame();
    }

    /**
     * A játékos kap egy új használható tárgyat.
     * @param item az új tárgy, amit kap a játékos.
     */ 
    public void addUsableItem(UsableItem item) {
        useableItems.add(item);
    }

    /**
     * A játékos megpróbál menteni egy másik játékost a vízbefulladástól.
     * @param p megmentendő játékos.
     * @param target megmententő játékos mezője.
     */ 
    public void saveMe(Player p, Tile target){
        rope.save(p, target, tile);
    }

    /**
     * Megsebzi a játékos.
     * @param amount ennyi testhőt veszít el a játékos.
     */
    public void damage(int amount){
        // nem kell még?
    }

    /**
     * Gyógyítja a játékost.
     * @param amount ennyi testhőt nyer a játékos.
     */ 
    public void heal(int amount){
        // nem kell még?
    }

    /**
     * A játékos kap egy új búvárruhát.
     * @param ds az új búvárruha, amit kap a játékos.
     */
    public void addDivingSuit(DivingSuit ds){
        divingSuit = ds;
    }
    /**
     * A játékos kap egy új kötelet.
     * @param r az új kötél, amit kap a játékos.
     */
    public void addRope(Rope r){
        rope = r;
    }
}