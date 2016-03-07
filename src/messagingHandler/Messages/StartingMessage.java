package messagingHandler.Messages;

import gameInterface.Dialogs.StartingDialog;
import messagingHandler.Actions.Action;
import messagingHandler.GameAdapter;

public class StartingMessage implements Message, Action {
    public StartingMessage() {}

    @Override
    public void activateMessage() {
        StartingDialog dialog = new StartingDialog();
        dialog.setVisible(true);
    }
}
