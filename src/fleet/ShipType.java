package fleet;

/**
 * Created by adrabik on 19.02.16.
 */
public enum ShipType {
    SS(1),DD(2),CA(3),BB(4);

    public final int shipLength;

    ShipType(int shipLength) {
        this.shipLength = shipLength;
    }
}
