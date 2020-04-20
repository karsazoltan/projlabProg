package sumatra;

import java.util.ArrayList;
import java.util.Random;


/**
 * A stabil jégtáblát reprezentáló osztály
 */
public class Tile {
    /**
     * A táblán található hó mélysége
     */
    protected int snowlayers;
    /**
     * A tábla teherbírása
     */
    protected int capacity;
    /**
     * A szomszédos mezők listája
     */
    protected ArrayList<Tile> neighbors;
    /**
     * Változó ami megadja, hogy játékosok ismerik-e a tábla teherbírása
     */
    private boolean is_capacity_known;
    /**
     * A táblán található játékosok
     */
    protected ArrayList<Creature> creatures;
    /**
     * A táblán található tárgy
     */
    protected Item item;
    /**
     * A táblára épített építmény
     */
    private Building building;

    /**
     * Ez tárolja a válozó nevét, a szkeletonban a kiíráshoz kell.
     */
    protected String objName;

    /**
     * Default konstruktor
     */
    public Tile(String objName) {
        this.objName = objName;
        snowlayers = 0;
        capacity = -1;
        is_capacity_known = false;
        building = new NoBuilding("noBuilding");
        neighbors = new ArrayList<>();
        creatures = new ArrayList<>();
    }

    /**
     * Getter ami a hó mélységét adja vissza
     * @return A hó ménysége
     */
    public int getSnowlayers() {
        return snowlayers;
    }

    /**
     * Setter a hó mélységére
     * @param snowlayers Beállítani kívánt hó mélység
     */
    public void setSnowlayers(int snowlayers) {
        this.snowlayers = snowlayers;
    }

    /**
     * Getter a teherbírása
     * @return A tábla teherbírása
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Setter a teherbírása
     * @param capacity Beállítani kívánt teherbírás
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Getter a tábln található tárgyra
     * @return A táblán található tárgy
     */
    public Item getItem() {
        return item;
    }

    /**
     * Getter a táblán található építményre
     * @return A táblán található építmény
     */
    public Building getBuilding() {
        return building;
    }

    /**
     * Elhelyez egy tárgyat a táblán ha még nincs rajta
     * @param i Az elhelyezni kívánt tárgy
     * @return Az elhelyezés sikeressége
     */
    public boolean placeItem(Item i) {
        Skeleton.printLine(this.objName, "placeItem()");

        if (item != null) {
            return false;
        }
        item = i;
        return true;
    }

    /**
     * Átadja a játékosnak a tárgyat
     * @param p A játékos aki felveszi a tárgyat
     */
    public void pickUpItem(Player p) {
        item.giveToPlayer(p);
    }

    /**
     * Elfogadja a táblára lépő élőlényt
     * @param c A táblára lépő élőlény
     */
    public void accept(Creature c) {
        creatures.add(c);
    }

    /**
     * Eltávolítja a Tábláról a élőlényt
     * @param c Az eltávolítandő élőlény
     */
    public void remove(Creature c) {
        creatures.remove(c);
    }

    /**
     * Hozzáad egy táblát a szomszédokhoZ
     * @param t A szomszéd tábla
     */
    public void addNeighbor(Tile t) {
        neighbors.add(t);
    }

    /**
     * Havat helyez a táblára
     * @param amount A hó mélysége
     */
    public void addSnow(int amount) {
        snowlayers += amount;
    }

    /**
     * Eltávolít havat a tábláról
     * @param amount Az eltávolítandó hó mélysége
     */
    public void removeSnow(int amount) {
        snowlayers -= amount;
        if (snowlayers < 0) {
            snowlayers = 0;
        }
    }

    /**
     * Visszaadja a szomszédos táblákon álló élőlényeket
     * @return A szomszédos élőlények
     */
    public ArrayList<Player> getNeighingCreatures() {
        ArrayList<Player> neighingcreatures = new ArrayList<>();
        for (Tile t : neighbors) {
            neighingcreatures.addAll(t.creatures);
        }

        return neighingcreatures;
    }

    /**
     * Felfedi a mező teherbírását
     */
    public void revealCapacity() {
        is_capacity_known = true;
    }

    /**
     * Vihart csinál a táblán
     */
    public void storm() {
        addSnow(new Random().nextInt(2));
        building.onStorm(creatures);
    }

    /**
     * A táblára helyez egy Igloo építményt
     */
    public void buildIgloo() {
        building = new Igloo("igloo");
    }
}
