package graphics;

import sumatra.Building;
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
        Building b = t.getBuilding();
        if(b != null) {
            if(b.getBuildingType().compareTo("igloo") == 0)
                label.setText("I");
            else if(b.getBuildingType().compareTo("tent") == 0)
                label.setText("T");
        }
    }
}
