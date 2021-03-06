package gameInterface.Listeners;

import gameInterface.ShipPlacementWindow;
import gameInterface.buttons.VisibleItem;
import gameInterface.buttons.ShipButton;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * Created by adrabik on 23.02.16.
 */
public class MovingButtonAdapter extends MouseInputAdapter {
    private ShipButton button;
    public static ShipButton currentMovingButton;

    public MovingButtonAdapter(ShipButton button) {
        this.button = button;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentMovingButton=button;
        if (!button.isEnabled()) return;
        button.relocate(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        currentMovingButton=null;
        if (!button.isEnabled()) return;
        int buttonX = button.getBounds().x;
        int buttonY = button.getBounds().y;
        Dimension shipSize = button.getShipSize();
        int buttonWidth = shipSize.width/2/(shipSize.width/ VisibleItem.itemSize);
        int buttonHeight = shipSize.height/2/(shipSize.height/ VisibleItem.itemSize);
        Point shipLocation = new Point(
                buttonX+buttonWidth ,
                buttonY+ buttonHeight );
        Point fieldPoint = ShipPlacementWindow.shipPlacementWindow.getNearestFieldLocation(shipLocation, shipSize);
        if (fieldPoint==null){
            button.returnToOriginalPosition();
        } else{
            button.setEnabled(false);
            button.placeOnBoard(fieldPoint);
            ShipPlacementWindow.shipPlacementWindow.repaint();
            ShipPlacementWindow.shipPlacementWindow.revalidate();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!button.isEnabled()) return;
        button.relocate(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (!button.isEnabled()) return;
        button.changeOrientation();
    }
}
