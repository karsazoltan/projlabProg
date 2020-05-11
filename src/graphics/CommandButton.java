package graphics;

import sumatra.Interpreter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A akciógombokat reprezentáló osztály
 */
public class CommandButton extends JButton implements ActionListener {
    private Command command;

    /**
     * Konstruktor
     * @param comm A gombhoz tartozó parancs
     */
    public CommandButton(Command comm) {
        command = comm;
    }

    /**
     * A gombhoz tartozó parancs getterje
     * @return A gomboz tartozó parancs
     */
    public Command getCommand() {
        return command;
    }

    /**
     * A gomb lenyomásával kiváltott esemény
     * @param e Az esemény
     */
    public void actionPerformed(ActionEvent e) {
        if (command.hasParameters()) {
            JDialog d = new JDialog();
            d.setSize(200, 90);
            d.setTitle(command.getParameterInfo());
            d.setLayout(new GridLayout(0, 1));

            JButton okButton = new JButton("Ok");
            Component generic;
            ActionListener lambda;

            if (command.hasValidOptions()) {
                final Integer[] options = command.getValidOptions().toArray(new Integer[0]);
                JComboBox<Integer> jcb = new JComboBox<>(options);
                generic = jcb;
                lambda = (arg) -> {
                    int index = jcb.getSelectedIndex();
                    if (index < 0) return;
                    Interpreter.interpretCommand(command.getCommand() + " " + options[index]);
                    CommandPanel.getInstance().updateButtons();
                    d.dispose();
                };
            } else {
                JTextField tf = new JTextField();
                generic = tf;
                lambda = (arg) -> {
                    Interpreter.interpretCommand(command.getCommand() + " " + tf.getText());
                    CommandPanel.getInstance().updateButtons();
                    d.dispose();
                };
            }
            generic.setSize(new Dimension(200,30));
            d.add(generic);

            okButton.addActionListener(lambda);

            d.add(okButton);
            d.setLocationRelativeTo(null);
            d.setVisible(true);
        } else {
            Interpreter.interpretCommand(command.getCommand());
            CommandPanel.getInstance().updateButtons();
        }
    }
}
