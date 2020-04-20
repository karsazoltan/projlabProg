package sumatra;

public class Bear extends Creature{
    @Override
    void playRound() {       
        // TODO
        // Move in random direction.
    }

    @Override
    void collideWith(Creature c) {
        c.hitBy(this);
    }

    @Override
    void fallInWater() {}

    @Override
    void damage(int amount) {}

    @Override
    void hitBy(Bear b) { }
}