package e2;

public interface Cell {
    /**
     *
     * @return whether the cell has a mine or not
     */
    boolean hasMine();

    /**
     *
     * @return whether the cell is flagged or not
     */
    boolean isFlagged();

    /**
     * Flag this cell.
     */
    void flag();

    /**
     * Remove the flag from this cell
     */
    void removeFlag();
}
