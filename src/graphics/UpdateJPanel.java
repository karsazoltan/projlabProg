package graphics;

import sumatra.Tile;

import javax.swing.*;
import java.awt.*;

public abstract class UpdateJPanel extends JPanel {
    protected JLabel label;
    public UpdateJPanel(JLabel reflabel) {
        label = reflabel;
        add(label);
        setVisible(true);
    }
    public abstract void Update(Tile t);
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(30, 30);
    };
}
