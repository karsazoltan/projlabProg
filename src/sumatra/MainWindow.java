package sumatra;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("Sumatra :: Jégmező");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel leftOuterPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(new FlarePartsPanel());
        leftPanel.add(new CreaturesPanel());
        leftPanel.setPreferredSize(new Dimension(200, 600));
        JSeparator leftSeparator = new JSeparator(SwingConstants.VERTICAL);
        leftSeparator.setPreferredSize(new Dimension(10, 600));
        leftOuterPanel.add(leftPanel, BorderLayout.CENTER);
        leftOuterPanel.add(leftSeparator, BorderLayout.LINE_END);

        add(leftOuterPanel, BorderLayout.LINE_START);
        add(new GameAreaPanel(), BorderLayout.CENTER);
        add(new CommandPanel(), BorderLayout.LINE_END);

        setVisible(true);
    }
}
