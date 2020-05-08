package graphics;

import sumatra.World;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("Sumatra :: Jégmező");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Belső layout //
        JPanel leftOuterPanel = new JPanel(new BorderLayout());
        leftOuterPanel.add(new FlarePartsPanel(), BorderLayout.PAGE_START);
        leftOuterPanel.add(CreaturesPanel.getInstance(), BorderLayout.CENTER);
        leftOuterPanel.setPreferredSize(new Dimension(200, 0));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(GameAreaPanel.getInstance(), BorderLayout.CENTER);
        centerPanel.add((new ConsoleLogger()).getComponent(), BorderLayout.PAGE_END);

        JPanel rightOuterPanel = new JPanel(new BorderLayout());
        rightOuterPanel.add(CommandPanel.getInstance(), BorderLayout.PAGE_START);

        // Menüsor //
        createMenuStrip();

        // Regisztrálás //
        add(leftOuterPanel, BorderLayout.LINE_START);
        add(centerPanel, BorderLayout.CENTER);
        add(rightOuterPanel, BorderLayout.LINE_END);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createMenuStrip() {
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

        setJMenuBar(menuBar);
    }
}
