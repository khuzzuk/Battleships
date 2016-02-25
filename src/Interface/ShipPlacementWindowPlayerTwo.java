package Interface;

import game.Game;
import game.Ship;

import java.awt.*;

public class ShipPlacementWindowPlayerTwo extends MainWindow {
    public ShipPlacementWindowPlayerTwo(Game game) {
        super(game);
    }

    @Override
    protected void showNextShip() {
        Ship ship = game.nextShipToPlacePlayerTwo();
        if (ship!=null)
            addShip(ship);
    }
    @Override
    public void placeShipOnBoard(Ship ship, Point... points){
        super.placeShipOnBoard(ship,points);
        if (game.placeShipPlayerTwo(ship, fieldsFromBoard)){
            showNextShip();
        }
        else {
            returnLastShip();
        }
    }
}
