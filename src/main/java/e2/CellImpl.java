package e2;

public class CellImpl implements Cell{

    private final boolean hasMine;
    private boolean flagged;

    public CellImpl(boolean hasMine) {
        this.hasMine = hasMine;
        this.flagged = false;
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
}
