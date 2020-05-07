package graphics;

import sumatra.Tile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BuldingView extends JPanel implements IUpdate {
    @Override
    public void Update(Tile t) {
        setBorder(new LineBorder(new Color(149, 119, 29)));
        //hogyha ...
        t.getBuilding();
        //TODO
    }
}
