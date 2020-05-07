package sumatra;

public interface IViewable {
    /**
     * Hozzád a tárolt nézetek közé még egyet.
     * @param v - a hozzáadott View
     */
    void addView(IView v);

    /**
     * Frissíti a nézeteket.
     */
    void updateViews();
}