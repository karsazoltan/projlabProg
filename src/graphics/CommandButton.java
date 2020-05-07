package graphics;

import sumatra.Interpreter;

import javax.swing.*;
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
        String cmd = command.getCommand();
        if (command.getParameters() != 0) {
            JDialog d = new JDialog();
            ArrayList<JTextField> tfs = new ArrayList<>();
            d.setTitle("Enter parameters");

            for (int i = 0; i < command.getParameters(); i++) {
                JTextField tf = new JTextField();
                tfs.add(tf);
                d.add(tf);
            }
            JButton okButton = new JButton("Ok");
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (JTextField tf : tfs) {
                        cmd.concat(" "+tf.getText());
                    }
                    d.setVisible(false);
                }
            });
            d.add(okButton);
            d.pack();
            d.setVisible(true);
        }

        Interpreter.interpretCommand(cmd);

        cp.updateButtons();
    }
}
