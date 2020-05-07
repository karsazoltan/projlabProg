package graphics;

import sumatra.World;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("Sumatra :: Jégmező");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Belső layout //
        JPanel leftOuterPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(new FlarePartsPanel());
        leftPanel.add(CreaturesPanel.getInstance());
        leftPanel.setPreferredSize(new Dimension(200, 600));
        JSeparator leftSeparator = new JSeparator(SwingConstants.VERTICAL);
        leftSeparator.setPreferredSize(new Dimension(5, 600));
        leftOuterPanel.add(leftPanel, BorderLayout.CENTER);
        leftOuterPanel.add(leftSeparator, BorderLayout.LINE_END);

        // Menüsor //
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem saveMI = new JMenuItem("Save");
        JMenuItem loadMI = new JMenuItem("Load");
        fileMenu.add(saveMI);
        fileMenu.add(loadMI);

        saveMI.addActionListener(e -> {
            JFileChooser jfk = new JFileChooser();

            if (jfk.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    GameAreaPanel.getInstance().saveLayout(jfk.getSelectedFile().getAbsolutePath());
                    CommandPanel.getInstance().updateButtons();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        loadMI.addActionListener(e -> {
            JFileChooser jfk = new JFileChooser();

            if (jfk.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    GameAreaPanel.getInstance().loadTileViewsFromFile(jfk.getSelectedFile().getAbsolutePath());
                    CommandPanel.getInstance().updateButtons();
                    CreaturesPanel.getInstance().attachViews();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JMenu initMenu = new JMenu("Initialization");
        menuBar.add(initMenu);

        JMenuItem worldInitMI = new JMenuItem("Init World");
        JMenuItem creatInitMI = new JMenuItem("Init Creatures");
        initMenu.add(worldInitMI);
        initMenu.add(creatInitMI);

        worldInitMI.addActionListener(e -> new InitWorldWindow());
        creatInitMI.addActionListener(e -> new InitCreatureWindow(World.getInstance().getTileCount()));


        // Regisztrálás //
        add(leftOuterPanel, BorderLayout.LINE_START);
        add(GameAreaPanel.getInstance(), BorderLayout.CENTER);
        add(CommandPanel.getInstance(), BorderLayout.LINE_END);
        setJMenuBar(menuBar);

        setVisible(true);
    }
}
