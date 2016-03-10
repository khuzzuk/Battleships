package gameInterface.Listeners;

import gameInterface.ShipPlacementWindow;
import gameInterface.buttons.VisibleItem;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class WindowResized implements ComponentListener{
    private int boardSize;
    private int additionalSpace = 0;

    public WindowResized(int boardSize) {
        this.boardSize = boardSize;
    }

    public WindowResized(int boardSize, int additionalSpace) {
        this.boardSize = boardSize;
        this.additionalSpace = additionalSpace;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        int width = e.getComponent().getWidth();
        int height = e.getComponent().getHeight();
        VisibleItem.setItemSize(Math.min(width/(boardSize+additionalSpace), (height-35)/(boardSize)));
        if (e.getComponent() instanceof ShipPlacementWindow){
            ShipPlacementWindow window = (ShipPlacementWindow)e.getComponent();
            window.remake();
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
