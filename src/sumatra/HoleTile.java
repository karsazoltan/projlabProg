package sumatra;

public class HoleTile extends Tile {
    @Override
    public boolean placeItem(Item i) {
        return false;
    }

    @Override
    public void buildIgloo() {
    }
}
