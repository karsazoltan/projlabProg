package graphics;

import sumatra.Tile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ItemView extends UpdateJPanel {
    public ItemView(JLabel reflabel) {
        super(reflabel);
        setBorder(new LineBorder(new Color(0, 0, 0)));
    }
    @Override
    public void Update(Tile t) {
        if(t.getItem() != null)
            label.setText("I");
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(30, 30);
    };
}
