package graphics;

import javax.swing.*;
import java.awt.*;

import java.awt.Color;

public class CreaturesPanel extends JPanel {
    // TODO STUB
    public CreaturesPanel(){        
        JLabel cLabel = new JLabel("Creatures");
        Font labelFont = cLabel.getFont();
        cLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
        add( cLabel);
        
    }
}
