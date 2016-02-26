package Interface;

import game.Game;
import game.fleet.Ship;

import java.awt.*;

public class ShipPlacementWindowPlayerOne extends MainWindow {
    public ShipPlacementWindowPlayerOne(Game game) {
        super(game);
    }

    @Override
    protected void showNextShip() {
        Ship ship = game.nextShipToPlacePlayerOne();
        if (ship!=null)
            addShip(ship);
    }
    @Override
    public void placeShipOnBoard(Ship ship, Point... points){
        super.placeShipOnBoard(ship,points);
        if (game.placeShipPlayerOne(ship, fieldsFromBoard)){
            showNextShip();
        }
        else {
            returnLastShip();
        }
        repaint();revalidate();
    }
}
