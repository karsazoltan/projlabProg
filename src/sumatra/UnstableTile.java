package sumatra;

public class UnstableTile extends Tile {
    public void flip() {
        Player[] players = getPlayers();
        for (int i = 0; i < getPlayers().size(); i++)
            players.fallInWater();
    }
}
