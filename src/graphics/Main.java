package graphics;

/**
 * Main osztály, itt indul az alkalmazás
 */
public class Main {

    public static MainWindow mw;

    /**
     * Main függvény, itt indul az alkalmazás
     * @param args Kapott argumentumok
     */
    public static void main(String[] args) {
        mw = new MainWindow();
    }

    /**
     * @return főablak, a dialógusablakok használják
     */
    public static MainWindow getMw() {
        return mw;
    }
}
