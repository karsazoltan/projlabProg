package graphics;

import sumatra.Building;
import sumatra.Tile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BuildingView extends UpdateJPanel {
    public BuildingView(JLabel reflabel) {
        super(reflabel);
        setBorder(new LineBorder(new Color(0, 0, 0)));
    }
    @Override
    public void Update(Tile t) {
        Building b = t.getBuilding();
        if(b != null && b.getBuildingType() != null) {
            if(b.getBuildingType().compareTo("igloo") == 0)
                label.setText("I");
            else if(b.getBuildingType().compareTo("tent") == 0)
                label.setText("T");
            else
                label.setText("-");
        } else
            label.setText("-");
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(30, 30);
    }
}
