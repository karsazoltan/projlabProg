package graphics;

import javax.swing.*;

import java.awt.Font;
import java.util.ArrayList;

import sumatra.Creature;

public class OneCreaturePanel extends JPanel implements IView{
    JLabel hpLabel = new JLabel();
    JLabel manaLabel = new JLabel();
    ArrayList<JLabel> iLabels = new ArrayList<JLabel>();
    Creature creature;

    public OneCreaturePanel(Creature pcreature){
        creature = pcreature;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel nameLabel = new JLabel(creature.getType() + creature.getIndex());
        Font labelFont = nameLabel.getFont();
        nameLabel.setFont(new Font(labelFont.getName(), Font.BOLD, 20));
        add(nameLabel);
        add(hpLabel);
        add(manaLabel);
        subjectChanged();
    }

    public void subjectChanged(){
        // TODO 
    }
}