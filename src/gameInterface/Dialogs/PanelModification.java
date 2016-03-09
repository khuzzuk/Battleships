package gameInterface.Dialogs;

import messagingHandler.MessageSender;
import messagingHandler.Messages.BoardSizeDecided;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;

public abstract class PanelModification {
    public static JPanel PreparePanel(JPanel panel) {
        GridBagLayout mainLayout = new GridBagLayout();
        panel.setLayout(mainLayout);
        return panel;
    }
    public static JFormattedTextField addTextField(Window base, JPanel panel, GridBagConstraints constr) throws ParseException {
        JFormattedTextField textField = new JFormattedTextField(new MaskFormatter("##"));
        constr.gridy++;
        panel.add(textField,constr);
        return textField;
    }
}
