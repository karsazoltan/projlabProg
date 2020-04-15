package sumatra;

/**
 * Absztrakt automata eszközöket reprezentáló osztály.
 */
public abstract class AutomaticItem extends Item {

    /**
     * Szkeleton konstruktor, meg lehet adni neki az objektum nevét
     * @param objName Az objektum, mint változó neve
     */
    public AutomaticItem(String objName) {
        super(objName);
    }
}
