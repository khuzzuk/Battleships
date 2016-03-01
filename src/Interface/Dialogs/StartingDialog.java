package Interface.Dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class StartingDialog extends JDialog implements GridLayoutSettings {

    private JFormattedTextField textField;

    public StartingDialog() {
        super();
        JPanel panel = setupJPanel();
        GridBagConstraints constr = setupConstraints();
        addLabel(panel, constr);
        addTextField(panel, constr);
        pack();
        setLocationRelativeTo(null);
    }

    private void addTextField(JPanel panel, GridBagConstraints constr) {
        textField = new JFormattedTextField(NumberFormat.getNumberInstance());
        textField.addActionListener(e -> {
            System.out.println(textField.getValue().toString());
            dispose();
        });
        constr.gridy=2;
        panel.add(textField,constr);
    }

    private void addLabel(JPanel panel, GridBagConstraints constr) {
        JLabel label = new JLabel("Welcome in the Battleships game.");
        constr.gridy=0;
        constr.gridx=0;
        panel.add(label,constr);
        JLabel secondLabel = new JLabel("Please, decide about board size.");
        constr.gridy = 1;
        panel.add(secondLabel,constr);
    }

    private JPanel setupJPanel() {
        JPanel panel = (JPanel) getContentPane();
        GridBagLayout mainLayout = new GridBagLayout();
        panel.setLayout(mainLayout);
        return panel;
    }
}
