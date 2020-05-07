package graphics;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class ConsoleLogger extends OutputStream {
    private JTextArea console;
    private JScrollPane wrapper;

    public ConsoleLogger() {
        console = new JTextArea();
        wrapper = new JScrollPane(console);

        wrapper.setPreferredSize(new Dimension(0, 150));
        console.setEditable(false);
        console.setBackground(UIManager.getColor("Panel.background"));

        System.setOut(new PrintStream(this));
    }

    @Override
    public void write(int i) throws IOException {
        String text = new String(new byte[] {(byte) i}, 0, 1);
        SwingUtilities.invokeLater(() -> console.append(text));
    }

    public JComponent getComponent() {
        return wrapper;
    }
}
