package game;

/**
 * Created by adrabik on 24.02.16.
 */
public interface PlaceableOnBoard {
    public boolean isPlacedOnBoard();
    public boolean canBePlacedWith(PlaceableOnBoard other);
}
