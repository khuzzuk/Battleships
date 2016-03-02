package fleet;

/**
 * Created by adrabik on 24.02.16.
 */
public interface PlaceableOnBoard {
    boolean isPlacedOnBoard();
    boolean canBePlacedWith(PlaceableOnBoard other);
}
