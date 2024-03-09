package e2;

public class CellImpl implements Cell{

    private final boolean hasMine;
    private boolean flagged;
    private boolean clicked;

    public CellImpl(boolean hasMine) {
        this.hasMine = hasMine;
        this.flagged = false;
        this.clicked = false;
    }

    public CellImpl() {
        this(false);
    }

    @Override
    public boolean hasMine() {
        return this.hasMine;
    }

    @Override
    public boolean isFlagged() {
        return this.flagged;
    }

    @Override
    public void flag() {
        this.flagged = true;
    }

    @Override
    public void removeFlag() {
        this.flagged = false;
    }

    @Override
    public boolean isClicked() {
        return clicked;
    }

    @Override
    public void click() {
        this.clicked = true;
    }
}
