package graphics;

import sumatra.Tile;

import javax.swing.*;
import java.awt.*;

public class ItemView extends UpdateJPanel {
    public ItemView(JLabel reflabel) {
        super(reflabel);
    }
    @Override
    public void Update(Tile t) {
        if(t.getItem() != null)
            label.setText("I");
        else
            label.setText("");
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(10, 10);
    };
}
