package graphics;

import javax.swing.*;

import java.awt.Font;
import java.util.ArrayList;

import sumatra.Creature;
import sumatra.Bear;

public class OneCreaturePanel extends JPanel implements IView{
    ArrayList<JLabel> labels = new ArrayList<JLabel>();
    Creature creature;

    public OneCreaturePanel(Creature pcreature){
        creature = pcreature;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel nameLabel = new JLabel(creature.getType() + creature.getIndex());
        Font labelFont = nameLabel.getFont();
        nameLabel.setFont(new Font(labelFont.getName(), Font.BOLD, 20));
        add(nameLabel);
    
        subjectChanged();
    }

    public void subjectChanged(){
        for( JLabel label : labels ){
            remove( label );
        }
        labels.clear();

        ArrayList<String> data = creature.getDisplayData();
        for( String str : data ){
            JLabel nLabel = new JLabel(str);
            add(nLabel);
            labels.add(nLabel);
        }
    }
}