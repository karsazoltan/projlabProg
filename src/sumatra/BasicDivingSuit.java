package sumatra;

/**
 * Búvárruha leszármazott, azt szimbolizálja, hogy a játékosnak van búvárruhája.
 */
public class BasicDivingSuit extends DivingSuit {

    public BasicDivingSuit(String objName) {
        super(objName);
    }

    /**
     * A ruhát birtokló játékos vízbeesésekor lefutó függvény. Ha a játékos ruhája ez, akkor
     * nincsen semmi gond, a játékos egyszerűen túléli a vízbeesést.
     * @param p A ruhát birtokló játékos.
     */
    @Override
    public void fallInWater(Player p) {
        Skeleton.printLine(objName, "fallInWater()");
        Skeleton.returned();
    }

}
