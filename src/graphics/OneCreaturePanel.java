package graphics;

import javax.swing.*;

import java.awt.Font;
import java.util.ArrayList;

import sumatra.Creature;

/**
 * Egy darab lénynek, a játék bal oldalán levő megjelenítésével foglalkozó panel.
 */
public class OneCreaturePanel extends JPanel implements IView{
    /* A lény adatait megjelenítő szövegek */
    ArrayList<JLabel> labels = new ArrayList<>();
    /* Referencia a hozzátartozó lényre. */
    Creature creature;

    /* Konstruktor */
    public OneCreaturePanel(Creature pcreature){
        creature = pcreature;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel nameLabel = new JLabel(creature.getType() + " " + creature.getIndex());
        Font labelFont = nameLabel.getFont();
        nameLabel.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        add(nameLabel);
    
        subjectChanged();
    }

    /**
     * Megváltozott a lény.
     */
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

        revalidate();
    }
}