package messagingHandler.Actions;

import gameInterface.Dialogs.StartingDialog;
import messagingHandler.Subscribers.Subscriber;

public class StartingAction implements Action {

    @Override
    public void sendToSubscriber(Subscriber sub) {
        StartingDialog dialog = new StartingDialog();
        dialog.setVisible(true);
    }
}
