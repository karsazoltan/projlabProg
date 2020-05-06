package graphics;

import javax.swing.*;

import sumatra.World;

import java.awt.*;

import java.awt.Color;
import java.util.ArrayList;

import sumatra.Creature;

public class CreaturesPanel extends JPanel {
    public CreaturesPanel(){        
        JLabel cLabel = new JLabel("Creatures");
        Font labelFont = cLabel.getFont();
        cLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
        add( cLabel);
    }

    public void attachViews(){
        ArrayList<Creature> creatures = World.getInstance().getCreatures();
        for( Creature c : creatures ){
            OneCreaturePanel ocp = new OneCreaturePanel(c);
            add( ocp );
            // do view thing too here TODO
        }
    }
}
