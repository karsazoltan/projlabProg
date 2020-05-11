package graphics;

import javax.swing.*;

import sumatra.World;

import java.awt.*;

import java.util.ArrayList;

/**
 * A játék bal oldalán fent, eddig összegyűjtött jelző rakéta darabokat megjelenítő panel.
 */
public class FlarePartsPanel extends JPanel implements IView {
    /* Az eddig összegyűjtött rakétadaraboknak egy-egy label */
    private ArrayList<JLabel> pLabels = new ArrayList<>();

    /* Konstruktor */
    public FlarePartsPanel(){      
        World.getInstance().addView(this);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        JLabel fLabel = new JLabel("Flareparts");
        Font labelFont = fLabel.getFont();
        fLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
        add( fLabel);
    }

    /**
     * Megváltozott az eddig összegyűjtött rakétadarabok száma. (új darabot vett fel valaki)
     */
    public void subjectChanged(){
        ArrayList<String> names = World.getInstance().getFlarepartNames();
        for( JLabel lab : pLabels ){
            remove( lab );
        }

        for( String name : names ){
            JLabel newLabel = new JLabel(name);
            add( newLabel );
            pLabels.add(newLabel);                
        }
        revalidate();
    }
}
