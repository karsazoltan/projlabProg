package sumatra;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Tile {
    protected int snowlayers;
    protected int capacity;
    protected ArrayList<Tile> neighbors;
    private boolean is_capacity_known;
    private ArrayList<Player> players;
    protected Item item;
    private Building building;

    public Tile() {
        snowlayers = 0;
        capacity = -1;
        is_capacity_known = false;
        building = new NoBuilding();
        players = new ArrayList<>();
    }

    public int getSnowlayers() {
        return snowlayers;
    }

    public void setSnowlayers(int snowlayers) {
        this.snowlayers = snowlayers;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Item getItem() {
        return item;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public boolean placeItem(Item i) {
        if (item != null) {
            item = i;
            return false;
        }
        return true;
    }

    public void pickUpItem(Player p) {
        p.pickUpItem();
    }

    public void accept(Player p) {
        players.add(p);
    }

    public void remove(Player p) {
        players.remove(p);
    }

    public void addNeighbor(Tile t) {
        neighbors.add(t);
    }

    public void addSnow(int amount) {
        snowlayers += amount;
    }

    public void removeSnow(int amount) {
        snowlayers -= amount;
        if (snowlayers < 0) {
            snowlayers = 0;
        }
    }

    public ArrayList<Player> getNeighingPlayers() {
        ArrayList<Player> neighingplayer = new ArrayList<>();
        for (Tile t : neighbors) {
            neighingplayer.addAll(t.players);
        }
        return neighingplayer;
    }

    public void revealCapacity() {
        is_capacity_known = true;
    }

    public void storm() {
        building.onStorm(players);
    }

    public void buildIgloo() {
        building = new Igloo();
    }
}
