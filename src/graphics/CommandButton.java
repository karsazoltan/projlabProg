package graphics;

import sumatra.Interpreter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CommandButton extends JButton implements ActionListener {
    private CommandPanel cp;
    private Command command;

    public CommandButton(Command comm, CommandPanel cp) {
        command = comm;
        this.cp = cp;
    }

    public Command getCommand() {
        return command;
    }

    public void actionPerformed(ActionEvent e) {
        if (command.hasParameters()) {
            JDialog d = new JDialog();
            d.setSize(200, 90);
            d.setTitle(command.getParameterInfo());
            d.setLayout(new GridLayout(0, 1));

            JTextField tf = new JTextField();
            tf.setSize(new Dimension(200,30));
            d.add(tf);

            JButton okButton = new JButton("Ok");
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Interpreter.interpretCommand(command.getCommand() + " " + tf.getText());
                    cp.updateButtons();
                    d.dispose();
                }
            });

            d.add(okButton);
            d.setLocationRelativeTo(null);
            d.setVisible(true);
        } else {
            Interpreter.interpretCommand(command.getCommand());
            cp.updateButtons();
        }
    }
}
