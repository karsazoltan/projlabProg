package graphics;

import sumatra.World;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/** A fő ablak osztálya */
public class MainWindow extends JFrame {
    /** Konstruktor, létrehoz és elindít mindent */
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
        JScrollPane scrollPane = new JScrollPane(GameAreaPanel.getInstance());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
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

    /**
     * Létrehozza a menüsort. Külön függvény, mert elég hosszú, és így
     * olvashatóbb a konstruktor
     */
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
                    JOptionPane jop = new JOptionPane("Error: Couldn't save file!", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = jop.createDialog("Error!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
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
                    World.getInstance().updateViews();
                } catch (IOException ex) {
                    JOptionPane jop = new JOptionPane("Error: Couldn't load file!", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = jop.createDialog("Error!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
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
        creatInitMI.addActionListener(e -> {
            if (World.getInstance().getTileCount() == 0)
                JOptionPane.showMessageDialog(null, "Error: Initialize a world first!", "Error!", JOptionPane.ERROR_MESSAGE);
            else
                new InitCreatureWindow(World.getInstance().getTileCount());
        });

        setJMenuBar(menuBar);
    }
}
