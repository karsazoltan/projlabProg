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
     * Ez tárolja a válozó nevét, a szkeletonban a kiíráshoz kell.
     */
    protected String objName;

    /**
     * A játékos konstruktora, meg kell adni mezőt, ahol kezdetben van a játékos.
     * @param startTile a mező melyen a játékos kezdetben van.
     */
    public Player(Tile startTile, String pobjName){
        objName = pobjName;
        tile = startTile;
        divingSuit = new NoDivingSuit("noDivingSuit");
        rope = new NoRope("noRope");
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
        Skeleton.printLine(this.objName, "move()");
        boolean hasMana = Skeleton.askQuestion("Van-e még munkaegysége?");
        if( hasMana ){
            tile.remove(this);
            newTile.accept(this);

            tile = newTile;
        }

        Skeleton.returned();
    }

    /**
     * A játékost egy új mezőre teszi, függetlenül attól, hogy van-e munkaegysége.
     * @param newTile a mező, ahol a játékos lesz.
     */
    public void forceMove(Tile newTile) {
        Skeleton.printLine(this.objName, "forceMove()");

        tile.remove(this);
        newTile.accept(this);

        tile = newTile;

        Skeleton.returned();
    }
    /**
     * Ás egyet a játékos, eltüntet egy réteg havat a mezőjéről.
     */ 
    public void dig() {
        Skeleton.printLine(this.objName, "dig()");

        boolean hasMana = Skeleton.askQuestion("Van-e még munkaegysége?");
        if( hasMana ){
            tile.removeSnow(1);
        }

        Skeleton.returned();
    }


    /**
     * A játékos beleesik a vízbe.
     */ 
    public void fallInWater() {     
        Skeleton.printLine(this.objName, "fallInWater()");

        if (divingSuit.fallInWater(this)) {
            World.getInstance().loseGame();
        }

        Skeleton.returned();
    }

    /**
     * A játékos használ egy tárgyat a táskájából.
     * @param index használandó tárgy indexe a játékos táskájában.
     * @param target mező amin felhasználja a játékos a tárgyat.
     */ 
    public void useItem(int index, Tile target) {
        Skeleton.printLine(this.objName, "useItem()");

        boolean hasMana = Skeleton.askQuestion("Van-e még munkaegysége?");
        if( hasMana ){
            useableItems.get(index).use(target);
        }

        Skeleton.returned();
    }

    /**
     * A játékos felvesz egy tárgyat a mezőjéről.
     */ 
    public void pickUpItem() {        
        Skeleton.printLine(this.objName, "pickUpItem()");

        boolean hasMana = Skeleton.askQuestion("Van-e még munkaegysége?");
        if( hasMana ){
            tile.pickUpItem(this);
        }
        Skeleton.returned();
    }

    /**
     * A játékos összerakja a flare-t, ha ez sikerül befejeződik a játék.
     */ 
    public void buildFlare() {
        Skeleton.printLine(this.objName, "buildFlare()");

        World.getInstance().checkEndGame();

        Skeleton.returned();
    }

    /**
     * A játékos kap egy új használható tárgyat.
     * @param item az új tárgy, amit kap a játékos.
     */ 
    public void addUsableItem(UsableItem item) {
        Skeleton.printLine(this.objName, "addUsableItem()");

        useableItems.add(item);

        Skeleton.returned();
    }

    /**
     * A játékos megpróbál menteni egy másik játékost a vízbefulladástól.
     * @param p megmentendő játékos.
     * @param target megmententő játékos mezője.
     * @return a kimentés sikeressége.
     */ 
    public Boolean saveMe(Player p, Tile target){
        Skeleton.printLine(this.objName, "saveMe()");

        boolean result = rope.save(p, target, tile);

        Skeleton.returned();
        return result;
    }

    /**
     * Megsebzi a játékos.
     * @param amount ennyi testhőt veszít el a játékos.
     */
    public void damage(int amount){
        Skeleton.printLine(objName, "damage()");
        Skeleton.returned();
    }

    /**
     * Gyógyítja a játékost.
     * @param amount ennyi testhőt nyer a játékos.
     */ 
    public void heal(int amount){
        Skeleton.printLine(objName, "heal()");
        Skeleton.returned();
    }

    /**
     * A játékos kap egy új búvárruhát.
     * @param ds az új búvárruha, amit kap a játékos.
     */
    public void addDivingSuit(DivingSuit ds){
        Skeleton.printLine(this.objName, "addDivingSuit()");

        divingSuit = ds;

        Skeleton.returned();
    }
    /**
     * A játékos kap egy új kötelet.
     * @param r az új kötél, amit kap a játékos.
     */
    public void addRope(Rope r){
        Skeleton.printLine(this.objName, "addRope()");
        
        rope = r;

        Skeleton.returned();
    }

}