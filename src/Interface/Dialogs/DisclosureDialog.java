package Interface.Dialogs;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

/**
 * Created by Adrian on 19.02.2016.
 */
public class DisclosureDialog extends JDialog implements GridLayoutSettings {

    private JButton okButton;

    public DisclosureDialog() {
        super();
        JPanel panel = setupJPanel();
        GridBagConstraints constr = setupConstraints();
        addLabel(panel, constr);
        okButton(panel, constr);
        pack();
    }

    private void okButton(JPanel panel, GridBagConstraints constr) {
        okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            dispose();
        });
        constr.gridy=2;
        panel.add(okButton,constr);
    }

    private void addLabel(JPanel panel, GridBagConstraints constr) {
        JLabel label = new JLabel("Pamiętaj, możesz postawić statek tylko raz,");
        constr.gridy=0;
        constr.gridx=0;
        panel.add(label,constr);
        JLabel secondLabel = new JLabel("Po umieszczeniu statku na planszy nie można zmienić decyzji.");
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
