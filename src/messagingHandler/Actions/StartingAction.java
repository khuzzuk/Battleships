package messagingHandler.Actions;

import gameInterface.Dialogs.StartingDialog;
import messagingHandler.Subscriber;

public class StartingAction implements Action {

    @Override
    public void notify(Subscriber sub) {
        StartingDialog dialog = new StartingDialog();
        dialog.setVisible(true);
    }
}
