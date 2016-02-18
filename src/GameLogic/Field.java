package GameLogic;

/**
 * Created by Adrian on 18.02.2016.
 */
public class Field {
    Mark mark;
    public Field() {
        mark = Mark.EMPTY;
    }

    public void hit() {
        mark = Mark.HITTED;
    }

    public Mark status() {
        return mark;
    }
}
