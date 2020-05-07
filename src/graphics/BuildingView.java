package graphics;

import sumatra.Tile;

import javax.swing.*;
import java.awt.*;

public class BuildingView extends UpdateJPanel {
    JLabel label;
    public BuildingView() {
        label = new JLabel("");
        add(label);
        setVisible(true);
    }
    @Override
    public void Update(Tile t) {
        setBorder(BorderFactory.createTitledBorder("Building"));
        //hogyha ...
        t.getBuilding();
    }
}
