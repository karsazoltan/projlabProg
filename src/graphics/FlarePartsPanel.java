package graphics;

import javax.swing.*;

import sumatra.World;

import java.awt.*;

import java.awt.Color;
import java.util.ArrayList;

public class FlarePartsPanel extends JPanel implements IView {
    private JTextPane textPane;
    private ArrayList<JLabel> pLabels = new ArrayList<JLabel>();

    public FlarePartsPanel(){      
        World.getInstance().addView(this);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        JLabel fLabel = new JLabel("Flareparts");
        Font labelFont = fLabel.getFont();
        fLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
        add( fLabel);
        
        JLabel newLabel = new JLabel("ez a swing kurvaszar");
        add( newLabel );        
        pLabels.add(newLabel);
    }

    public void subjectChanged(){
        ArrayList<String> names = World.getInstance().getFlarepartNames();
        for( String name : names ){
            boolean isNew = true;
            for( JLabel label : pLabels ){
                if( label.getText() == name ) isNew = false;
            }

            if( isNew ){
                JLabel newLabel = new JLabel(name);
                add( newLabel );
                pLabels.add(newLabel);                
            }
        }
        repaint();
    }
}
