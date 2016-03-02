package player;

public class PlayerNumber {
    public final int number;

    public PlayerNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerNumber that = (PlayerNumber) o;

        return number == that.number;

    }

    @Override
    public int hashCode() {
        return number;
    }
}
