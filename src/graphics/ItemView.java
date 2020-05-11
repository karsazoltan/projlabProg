package graphics;

import sumatra.Tile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Az itemek grafikus modellje
 */
public class ItemView extends UpdateJPanel {
    /**
     * Alap ctor
     * @param reflabel információk megjelnítéséhez
     */
    public ItemView(JLabel reflabel) {
        super(reflabel);
        setBorder(new LineBorder(new Color(0, 0, 0)));
    }

    /**
     * A nézet frissítése
     * @param t az itemhez tartozó tile
     */
    @Override
    public void Update(Tile t) {
        label.setText("");
        if(t.getItem() != null)
            label.setText("I");
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(30, 30);
    }
}
