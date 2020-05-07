package graphics;

import sumatra.Tile;

import javax.swing.*;
import java.awt.*;

public class ItemView extends UpdateJPanel {
    JLabel label;
    public ItemView() {
        label = new JLabel("");
        add(label);
        setVisible(true);
    }
    @Override
    public void Update(Tile t) {
        setBorder(BorderFactory.createTitledBorder("Item"));
        if(t.getItem() != null)
            label.setText("I");
        else
            label.setText("");
    }
}
