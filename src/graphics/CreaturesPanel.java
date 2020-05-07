package graphics;

import javax.swing.*;

import sumatra.World;

import java.awt.*;

import java.awt.Color;
import java.util.ArrayList;

import sumatra.Creature;

public class CreaturesPanel extends JPanel {
    JPanel panel = new JPanel();
    public CreaturesPanel(){        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        JLabel cLabel = new JLabel("Creatures");
        Font labelFont = cLabel.getFont();
        cLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
        add( cLabel);
        
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add( panel );
    }

    public void attachViews(){
        ArrayList<Creature> creatures = World.getInstance().getCreatures();
        for( Creature c : creatures ){
            OneCreaturePanel ocp = new OneCreaturePanel(c);
            panel.add( ocp );
            // do view thing too here TODO
        }
    }
}
