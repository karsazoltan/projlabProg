package sumatra;


public interface IView {
    /**
     * Jelzi a hozzá tartozó objektumnak, hogy megváltozott, firssíteni kell a grafikus felületet.
     */
    void subjectChanged();
}