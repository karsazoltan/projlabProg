package graphics;

import sumatra.Tile;

import javax.swing.*;
import java.awt.*;

public abstract class UpdateJPanel extends JPanel {
    public abstract void Update(Tile t);
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(20, 20);
    };
}
