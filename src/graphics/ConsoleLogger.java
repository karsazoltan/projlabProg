package graphics;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class ConsoleLogger extends OutputStream {
    private JTextArea console;

    public ConsoleLogger() {
        console = new JTextArea();
        console.setPreferredSize(new Dimension(0, 150));
        console.setEditable(false);
        console.setBackground(UIManager.getColor("Panel.background"));
        console.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.LIGHT_GRAY));

        System.setOut(new PrintStream(this));
    }

    @Override
    public void write(int i) throws IOException {
        String text = new String(new byte[] {(byte) i}, 0, 1);
        SwingUtilities.invokeLater(() -> console.append(text));
    }

    public JTextArea getComponent() {
        return console;
    }
}
