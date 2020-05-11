package sumatra;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

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
        usableItems = new ArrayList<>();

        mana = 4;
    }


    /**
     * Absztrakt függvény, ezt implementálják a játékos típusok, hogy különböző képességeik legyenek.
     */ 
    abstract public void useAbility(Tile target);

    /**
     * Ellenőrzi, hogy van-e manája a playernek, ha nincs, kiír egy hibaüzenetet
     * @return Van-e még manája a playernek
     */
    private boolean checkMana() {
        if (mana == 0) {
            System.out.println("    > You don't have enough work units left for that action!");
        }
        return mana > 0;
    }

    /**
     * Csökkenti a munkaegységek számát, és szól a nézeteknek, hogy ez megváltozott.
     */
    protected void decreaseMana() {
        mana--;
        updateViews();
    }

    /**
     * A játékost egy új mezőre lépteti.
     * @param newTile a mező, ahova a játékos lép.
     */
    public void move(Tile newTile) {
        boolean isNearby = tile.isNeighbor(newTile);
        if( checkMana() && isNearby ){
            tile.remove(this);
            tile = newTile;
            newTile.accept(this);

            decreaseMana();
        }
        if (!isNearby) {
            System.out.println("> Error: You can't do this - it's not a neighboring tile");
        }
    }

    /**
     * A játékost egy új mezőre teszi, függetlenül attól, hogy van-e munkaegysége és, hogy szomszédos-e a mező.
     * @param newTile a mező, ahol a játékos lesz.
     */
    public void forceMove(Tile newTile) {
        tile.remove(this);
        tile = newTile;
        newTile.accept(this);
        updateViews();
    }
    /**
     * Ás egyet a játékos, eltüntet egy réteg havat a mezőjéről.
     */ 
    public void dig() {
        if( checkMana() ){
            tile.removeSnow(1);
            decreaseMana();
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
        if( checkMana() ){
            usableItems.get(index).use(target);
            decreaseMana();
        }
    }

    /**
     * A játékos felvesz egy tárgyat a mezőjéről.
     */ 
    public void pickUpItem() {        
        if( checkMana() ){
            tile.pickUpItem(this);
            decreaseMana();
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
        updateViews();
    }

    /**
     * A játékostól elvesz egy használható tárgyat.
     * @param item a használható tárgy, amit elveszünk a játékostól.
     */
    public void removeUsableItem(UsableItem item) {
        usableItems.remove(item);
        updateViews();
    }

    /**
     * A játékos megpróbál menteni egy másik játékost a vízbefulladástól.
     * @param p megmentendő játékos.
     * @param target megmententő játékos mezője.
     * @return a kimentés sikeressége.
     */ 
    public boolean saveMe(Player p, Tile target){
        return rope.save(p, target, tile);
    }

    /**
     * Megsebzi a játékos.
     * @param amount ennyi testhőt veszít el a játékos.
     */
    public void damage(int amount){
        System.out.println("Player " + index + " lost " + amount + " health");
        health -= amount;
        updateViews();
        if( health <= 0 )
            World.getInstance().loseGame();
    }

    /**
     * Gyógyítja a játékost.
     * @param amount ennyi testhőt nyer a játékos.
     */ 
    public void heal(int amount){
        health += amount;
        updateViews();
    }

    /**
     * A játékos kap egy új búvárruhát.
     * @param ds az új búvárruha, amit kap a játékos.
     */
    public void addDivingSuit(DivingSuit ds){
        divingSuit = ds;
        updateViews();
    }
    /**
     * A játékos kap egy új kötelet.
     * @param r az új kötél, amit kap a játékos.
     */
    public void addRope(Rope r){
        rope = r;
        updateViews();
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
        System.out.println("> Player " + index + ", you're up!");
        mana = 4;        
        updateViews();
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


    /**
     * Visszaad egy lista sztringet a játékos állapotáról, a megjelenítéshez kell.
     * @return egy lista sztring.
     */
    @Override
    public ArrayList<String> getDisplayData() {
        ArrayList<String> data = new ArrayList<>();
        data.add( "HP: " + health );
        data.add( "WU: " + mana );
        if( !divingSuit.toString().equals("none") )
            data.add( divingSuit.toString() );
        if( !rope.toString().equals("none") )
            data.add( rope.toString() );

        for( Item i : usableItems ){
            data.add( i.toString() );
        }
        
        return data;
    }
}