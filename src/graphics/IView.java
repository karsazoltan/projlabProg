package graphics;

/**
 * Obszerver interfész
 */
public interface IView {
    /**
     * Meghívódik, ha az az IViewable objektum, ahová az IView be van regisztrálva, megváltozik.
     */
    void subjectChanged();
}
