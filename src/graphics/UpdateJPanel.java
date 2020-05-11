package graphics;

import sumatra.Tile;

import javax.swing.*;
import java.awt.*;

/**
 * Közös absztrakt osztály, a frissíthető nézetek megjelenítéséhez
 */
public abstract class UpdateJPanel extends JPanel {
    /**
     * információk megjelenítésére
     */
    protected JLabel label;

    /**
     * Alap ctor
     * @param reflabel label infók megjelnítésére
     */
    public UpdateJPanel(JLabel reflabel) {
        label = reflabel;
        add(label);
        setVisible(true);
    }

    /**
     * Fríssíti magát a nézet
     * @param t a paraméter alapján
     */
    public abstract void Update(Tile t);
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(30, 30);
    }
}
