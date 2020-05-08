package graphics;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/** Swing "komponens", ami magához irányítja át az STDOUTot */
public class ConsoleLogger extends OutputStream {
    /** A TextArea, ahol az STDOUT megjelenik */
    private JTextArea console;
    /** Egy ScrollPane, ami körbeveszi ezt a TextAreát */
    private JScrollPane wrapper;

    /** Konstruktor, beállítja a méreteket, magához irányítja az STDOUTot */
    public ConsoleLogger() {
        console = new JTextArea();
        wrapper = new JScrollPane(console);

        wrapper.setPreferredSize(new Dimension(0, 150));
        console.setEditable(false);
        console.setBackground(UIManager.getColor("Panel.background"));

        System.setOut(new PrintStream(this));
    }

    /**
     * Az OutputStream függvénye, ez lényegében az "átirányítás"
     * @param i A byte, amit ki kell írnunk
     * @throws IOException Ha nem tudunk írni
     */
    @Override
    public void write(int i) throws IOException {
        String text = new String(new byte[] {(byte) i}, 0, 1);
        SwingUtilities.invokeLater(() -> console.append(text));
    }

    /**
     * Visszatér a megjelenítendő Swing komponenssel
     * @return A megjelenítendő Swing komponens
     */
    public JComponent getComponent() {
        return wrapper;
    }
}
