package gameInterface.Listeners;

import gameInterface.ScallableWindow;
import gameInterface.ShipPlacementWindow;
import gameInterface.buttons.VisibleItem;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class WindowResized implements ComponentListener{
    private int boardSize;
    private int additionalSpace = 0;
    private int additionalHeight = 0;
    private int additionalWidth = 0;

    public WindowResized(int boardSize) {
        this.boardSize = boardSize;
    }

    public WindowResized(int boardSize, int additionalSpace) {
        this.boardSize = boardSize;
        this.additionalSpace = additionalSpace;
    }

    public WindowResized(int boardSize, int additionalHeight, int additionalWidth) {
        this.boardSize = boardSize;
        this.additionalHeight = additionalHeight;
        this.additionalWidth = additionalWidth;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        int width = e.getComponent().getWidth();
        int height = e.getComponent().getHeight();
        VisibleItem.setItemSize(Math.min((width-additionalWidth)/(boardSize+additionalSpace), (height-35-additionalHeight)/(boardSize)));
        if (e.getComponent() instanceof ScallableWindow){
            ScallableWindow window = (ScallableWindow)e.getComponent();
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
