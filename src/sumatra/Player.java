package sumatra;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <p>Absztrakt osztály, a játéksokat reprezentálja, leszármazottjai a különböző játékos típusok.</p>
 */
public abstract class Player extends Creature{
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
    ArrayList<UsableItem> usableItems;

    /** A játékos testhője. */
    protected int health;
    /** A játékos hátralevő munkái. */
    protected int mana;

    /**
     * A játékos konstruktora, meg kell adni mezőt, ahol kezdetben van a játékos.
     * @param startTile a mező melyen a játékos kezdetben van.
     */
    public Player(Tile startTile, int pindex){
        super(startTile, pindex);
        divingSuit = new NoDivingSuit();
        rope = new NoRope();
        usableItems = new ArrayList<UsableItem>();

        mana = 4;
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
        if( mana > 0 && tile.isNeighbor(newTile) ){
            tile.remove(this);
            newTile.accept(this);
            tile = newTile;

            --mana;
        }
    }

    /**
     * A játékost egy új mezőre teszi, függetlenül attól, hogy van-e munkaegysége és, hogy szomszédos-e a mező.
     * @param newTile a mező, ahol a játékos lesz.
     */
    public void forceMove(Tile newTile) {
        tile.remove(this);
        newTile.accept(this);

        tile = newTile;
    }
    /**
     * Ás egyet a játékos, eltüntet egy réteg havat a mezőjéről.
     */ 
    public void dig() {
        if( mana > 0 ){
            tile.removeSnow(1);
            --mana;
        }
    }


    /**
     * A játékos beleesik a vízbe.
     */ 
    public void fallInWater() {     
        if (divingSuit.fallInWater(this)) {
            World.getInstance().loseGame();
        }
    }

    /**
     * A játékos használ egy tárgyat a táskájából.
     * @param index használandó tárgy indexe a játékos táskájában.
     * @param target mező amin felhasználja a játékos a tárgyat.
     */ 
    public void useItem(int index, Tile target) {
        if( mana > 0 ){
            usableItems.get(index).use(target);
            --mana;
        }
    }

    /**
     * A játékos felvesz egy tárgyat a mezőjéről.
     */ 
    public void pickUpItem() {        
        if( mana > 0 ){
            tile.pickUpItem(this);
            --mana;
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
        usableItems.add(item);
    }

    /**
     * A játékos megpróbál menteni egy másik játékost a vízbefulladástól.
     * @param p megmentendő játékos.
     * @param target megmententő játékos mezője.
     * @return a kimentés sikeressége.
     */ 
    public boolean saveMe(Player p, Tile target){
        boolean result = rope.save(p, target, tile);

        return result;
    }

    /**
     * Megsebzi a játékos.
     * @param amount ennyi testhőt veszít el a játékos.
     */
    public void damage(int amount){
        health -= amount;
        if( health < 0 )
            World.getInstance().loseGame();
    }

    /**
     * Gyógyítja a játékost.
     * @param amount ennyi testhőt nyer a játékos.
     */ 
    public void heal(int amount){
        health += amount;
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

    /**
     * Visszaad egy tömböt, amelyben a játékos használható tárgyai vannak.
     * @return a játékos tárgyait tartalmazó tömb.
     */
    ArrayList<UsableItem> getItems(){
        return usableItems;
    }

    /** 
     * Visszaadja a játékos kötelét.
     * @return a játékos kötele.
     */
    Rope getRope(){
        return rope;
    }

    /**
     * Visszaadja a játékos búvárruháját.
     * @return a játékos búvárruhája.
     */
    DivingSuit getDivingSuit(){
        return divingSuit;
    }


    /**
     * Egy medve nekimegy a játékosnak, vége a játéknak.
     * @param b a medve.
     */
    @Override
    void hitBy(Bear b){
        World.getInstance().loseGame();
    }

    /**
     * A játékos ütközik egy másik lénnyel. Itt nem történik semmi, mert a játékos ha nekimegy valaminek
     * az eredménytelen, a medve megy neki a játékosoknak.
     * @param c a másik lény.
     */ 
    @Override
    void collideWith( Creature c ){}

    /**
     * A játékos lejátsza a körét.
     */
    @Override
    void playRound() {
        mana = 4;        
        boolean exit;
        Scanner input = World.getInstance().getInputScanner();
        do{
            String line = input.nextLine().trim();
            exit = Interpreter.interpretPlayerCommand(this, line);
        }while( !exit );
    }

    /**
     * Kiírja a megvalósító osztály adatait az átadott streamre
     *
     * @param stream ahova kiírjuk az adatokat
     * @param prefix Előtag (általában sok space)
     */
    @Override
    public void printData(OutputStream stream, String prefix) {
        PrintWriter pw = new PrintWriter(stream);
        pw.println(prefix + index + " " + type.toLowerCase() + " " + World.getInstance().getTileIndex(tile)
                + " " + health + " " + mana);
        pw.flush();
        rope.printData(stream, prefix + "    ");
        divingSuit.printData(stream, prefix + "    ");
        pw.println(prefix + "    usableitems " + usableItems.size());
        pw.flush();
        for (UsableItem i : usableItems) {
            i.printData(stream, prefix + "        ");
        }
        pw.flush();
    }
}