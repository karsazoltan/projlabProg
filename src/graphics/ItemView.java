package graphics;

import sumatra.Tile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ItemView extends JPanel implements IUpdate{
    JLabel label;
    public ItemView() {
        label = new JLabel("");
        add(label);
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
