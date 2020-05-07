package graphics;

/**
 * Obszerválható objektumok interfésze
 */
public interface IViewable {
    /**
     * Hozzáad a tárolt nézetek közé még egyet.
     * @param v - a hozzáadott View
     */
    void addView(IView v);

    /**
     * Frissíti a nézeteket.
     */
    void updateViews();
}