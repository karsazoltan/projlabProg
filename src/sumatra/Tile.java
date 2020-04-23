package sumatra;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
    protected boolean is_capacity_known;
    /**
     * A táblán található játékosok
     */
    protected ArrayList<Creature> creatures;
    /**
     * A táblán található tárgy
     */
    protected Item item = null;
    /**
     * A táblára épített építmény
     */
    private Building building;

    /**
     * Default konstruktor
     */
    public Tile(int snowAmount) {
        snowlayers = snowAmount;
        capacity = -1;
        is_capacity_known = false;
        building = new NoBuilding();
        neighbors = new ArrayList<>();
        creatures = new ArrayList<>();
        type = "stable";
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
     * Visszadja a szomszédos mezőket
     * @return A szomszédos mezők tömbje
     */
    public ArrayList<Tile> getNeighbors() {
        return neighbors;
    }

    /**
     * Elhelyez egy tárgyat a táblán ha még nincs rajta
     * @param i Az elhelyezni kívánt tárgy
     * @return Az elhelyezés sikeressége
     */
    public boolean placeItem(Item i) {
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
        building.newCreature(c, creatures);
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
    public ArrayList<Creature> getNeighingCreatures() {
        ArrayList<Creature> neighingcreatures = new ArrayList<>();
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
     * Tent/Igloo hozzáadáshoz függvény
     * @param build - az épület
     */
    public void setBuilding(Building build) {
        building = build;
    }

    /**
     * Visszaadja a paraméterként kapott mezőről hogy szomszédos-e ezzel a mezővel
     * @param other A mező amivel szomszádos ez
     * @return Visszaadja, hogy szomszédosak-e a mezők
     */
	public boolean isNeighbor(Tile other) {
        boolean result = false;
        for( Tile neighbor : neighbors ){
            if( neighbor == other )
                result = true;
        }
		return result;
	}

    /**
     * Kiírja a tile adatait felhasználóbarát módon - a tiles parancs kimenetéhez kell.
     */
	public void listGameInfo() {
        int idx = World.getInstance().getTileIndex(this);
        String type = (is_capacity_known) ? "S" : "?";
        String visibleitem = (snowlayers == 0 && item != null) ? ", visible item" : "";
        System.out.println(idx + ": " + type + visibleitem);
    }

    /**
     * Betölt a bemeneti bufferedreaderből egy jégtáblát.
     * @param br A bemeneti fájlt tartalmazó, megfelelő állapotban lévő bufferedreader.
     * @return A létrehozott jégtábla
     * @throws InputMismatchException Ha nem valid a betöltött fájl tartalma
     */
    public static Tile fromConfig(BufferedReader br) throws InputMismatchException {
        try {
            String[] firstline = br.readLine().trim().split(" ");
            Tile t;
            int snow = Integer.parseInt(firstline[2]);
            switch (firstline[1]) {
                case "stable": t = new Tile(snow); break;
                case "unstable": t = new UnstableTile(snow, Integer.parseInt(firstline[6])); break;
                case "hole": t = new HoleTile(snow); break;
                default: throw new InputMismatchException("Invalid Tile config!");
            }
            switch (firstline[3]) {
                case "y": t.is_capacity_known = true; break;
                case "n": t.is_capacity_known = false; break;
                default: throw new InputMismatchException("Invalid Tile config!");
            }
            boolean hastent = false;
            switch (firstline[4]) {
                case "none": t.setBuilding(new NoBuilding()); break;
                case "igloo": t.setBuilding(new Igloo()); break;
                case "tent": hastent = true; break;
            }
            switch (firstline[5]) {
                case "none": break;
                case "food": t.placeItem(new Food()); break;
                case "shovel": t.placeItem(new Shovel()); break;
                case "brokenshovel": t.placeItem(new BrokenShovel()); break;
                case "beacon": t.placeItem(new Beacon()); break;
                case "gun": t.placeItem(new Gun()); break;
                case "cartridge": t.placeItem(new Cartridge()); break;
                case "tent": t.placeItem(new TentEquipment()); break;
                case "basicdivingsuit": t.placeItem(new BasicDivingSuit()); break;
                case "basicrope": t.placeItem(new BasicRope()); break;
                default: throw new InputMismatchException("Invalid Tile config!");
            }
            if (hastent) {
                int tps = Integer.parseInt(br.readLine().trim().split(" ")[1]);
                t.setBuilding(new Tent(t, tps));
            }
            return t;
        } catch (IOException | NumberFormatException e) {
            throw new InputMismatchException("Invalid Tile config!");
        }
    }

    /**
     * A tábla típusát tároló attribútum
     */
    protected String type;

    /**
     * Kiírja a megvalósító osztály adatait az átadott streamre
     *
     * @param stream ahova kiírjuk az adatokat
     * @param prefix Előtag (általában sok space)
     */
    @Override
    public void printData(OutputStream stream, String prefix) {
        PrintWriter pw = new PrintWriter(stream);
        String known = (is_capacity_known) ? "y" : "n";
        String itemstr = (item != null) ? item.toString() : "none";
        String cap = (capacity != -1) ? Integer.toString(capacity) : "";
        pw.println(prefix + World.getInstance().getTileIndex(this) + " " + type + " " + snowlayers +
                " " + known + " " + building.getBuildingType() + " " + itemstr + " " + cap);
        if (building.getBuildingType().equals("tent")) {
            pw.println(prefix + "    tentplacementstep " + World.getInstance().getTentPlacementStep((Tent) building));
        }
        pw.flush();
    }
}
