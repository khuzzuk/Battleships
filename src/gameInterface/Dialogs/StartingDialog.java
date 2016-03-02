package gameInterface.Dialogs;

import messagingHandler.MessageSender;
import messagingHandler.Messages.BoardSizeDecided;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.text.ParseException;

public class StartingDialog extends JDialog implements GridLayoutSettings {

    private JFormattedTextField textField;

    public StartingDialog() {
        super();
        try {
            JPanel panel = setupJPanel();
            GridBagConstraints constr = setupConstraints();
            addLabel(panel, constr);
            addTextField(panel, constr);
            pack();
            setLocationRelativeTo(null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void addTextField(JPanel panel, GridBagConstraints constr) throws ParseException {
        textField = new JFormattedTextField(new MaskFormatter("##"));
        textField.addActionListener(e -> {
            System.out.println(textField.getValue().toString());
            int boardSize = Integer.parseInt(textField.getValue().toString());
            MessageSender.send(new BoardSizeDecided(boardSize));
            dispose();
        });
        constr.gridy++;
        panel.add(textField,constr);
    }

    private void addLabel(JPanel panel, GridBagConstraints constr) {
        JLabel label = new JLabel("Welcome in the Battleships game.");
        constr.gridy=0;
        constr.gridx=0;
        panel.add(label,constr);
        JLabel secondLabel = new JLabel("Please, decide about board size.");
        constr.gridy++;
        panel.add(secondLabel,constr);
        JLabel thirdLabel = new JLabel("Type a number and press enter.");
        constr.gridy++;
        panel.add(thirdLabel,constr);
    }

    private JPanel setupJPanel() {
        JPanel panel = (JPanel) getContentPane();
        GridBagLayout mainLayout = new GridBagLayout();
        panel.setLayout(mainLayout);
        return panel;
    }
}
