package sumatra;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;


/**
 * A stabil jégtáblát reprezentáló osztály
 */
public class Tile implements Printable {
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
    protected ArrayList<Player> players;
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
        players = new ArrayList<>();
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
     * Getter a táblán található játékosokra
     * @return A táblán található játékosok
     */
    public ArrayList<Player> getPlayers() {
        Skeleton.printLine(this.objName, "getPlayers()");
        Skeleton.returned();
        return players;
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
            Skeleton.returned();
            return false;
        }
        item = i;
        Skeleton.returned();
        return true;
    }

    /**
     * Átadja a játékosnak a tárgyat
     * @param p A játékos aki felveszi a tárgyat
     */
    public void pickUpItem(Player p) {
        Skeleton.printLine(this.objName, "pickUpItem()");

        item.giveToPlayer(p);

        Skeleton.returned();
    }

    /**
     * Elfogadja a tábláról lépő játékost
     * @param p A tábláról lépő játékos
     */
    public void accept(Player p) {
        Skeleton.printLine(this.objName, "accept()");

        players.add(p);

        Skeleton.returned();
    }

    /**
     * Eltávolítja a Tábláról a játékost
     * @param p Az eltávolítandő játékos
     */
    public void remove(Player p) {
        Skeleton.printLine(this.objName, "remove()");

        players.remove(p);

        Skeleton.returned();
    }

    /**
     * Hozzáad egy táblát a szomszédokhoZ
     * @param t A szomszéd tábla
     */
    public void addNeighbor(Tile t) {
        Skeleton.printLine(this.objName, "addNeighbor()");

        neighbors.add(t);

        Skeleton.returned();
    }

    /**
     * Havat helyez a táblára
     * @param amount A hó mélysége
     */
    public void addSnow(int amount) {
        Skeleton.printLine(objName, "addSnow()");
        snowlayers += amount;

        Skeleton.returned();
    }

    /**
     * Eltávolít havat a tábláról
     * @param amount Az eltávolítandó hó mélysége
     */
    public void removeSnow(int amount) {
        Skeleton.printLine(this.objName, "removeSnow()");

        snowlayers -= amount;
        if (snowlayers < 0) {
            snowlayers = 0;
        }

        Skeleton.returned();
    }

    /**
     * Visszaadja a szomszédos táblákon álló játékosokat
     * @return A szomszédos játékosok
     */
    public ArrayList<Player> getNeighingPlayers() {
        Skeleton.printLine(this.objName, "getNeighingPlayers()");

        ArrayList<Player> neighingplayer = new ArrayList<>();
        for (Tile t : neighbors) {
            neighingplayer.addAll(t.players);
        }

        Skeleton.returned();

        return neighingplayer;
    }

    /**
     * Felfedi a mező teherbírását
     */
    public void revealCapacity() {
        Skeleton.printLine(this.objName, "revealCapacity()");

        is_capacity_known = true;

        Skeleton.returned();
    }

    /**
     * Vihart csinál a táblán
     */
    public void storm() {
        Skeleton.printLine(this.objName, "storm()");

        addSnow(new Random().nextInt(2));
        building.onStorm(players);

        Skeleton.returned();
    }

    /**
     * A táblára helyez egy Igloo építményt
     */
    public void buildIgloo() {
        Skeleton.printLine(this.objName, "buildIgloo()");

        building = new Igloo("igloo");

        Skeleton.returned();
    }

    /**
     * Kiírja a megvalósító osztály adatait az átadott streamre
     * @param stream ahova kiírjuk az adatokat
     */
    @Override
    public void printData(OutputStream stream) {

    }
}
