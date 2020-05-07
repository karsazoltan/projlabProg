package graphics;

import sumatra.Building;
import sumatra.Tile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BuildingView extends JPanel implements IUpdate {
    JLabel label;
    public BuildingView() {
        label = new JLabel("");
        add(label);
    }
    @Override
    public void Update(Tile t) {
        setBorder(BorderFactory.createTitledBorder("Building"));
        //hogyha ...
        t.getBuilding();
    }
}
