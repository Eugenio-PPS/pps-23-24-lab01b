package e2;

import java.util.function.Function;

public class LogicsImpl implements Logics {
    NeighbourList<Cell> board;

    public LogicsImpl(int size, Function<Pair<Integer, Integer>, Boolean> minePlacingAlgorithm) {
        this.board = new NeighbourListImpl<>(
                new Pair<>(size, size),
                (p) -> java.util.Optional.of(new CellImpl(minePlacingAlgorithm.apply(p)))
        );
    }

    @Override
    public boolean hasMine(Pair<Integer, Integer> minePosition) {
        return this.board.get(minePosition).get().hasMine();
    }

    @Override
    public void flag(Pair<Integer, Integer> cellPosition) {
        this.board.get(cellPosition).get().flag();
    }

    @Override
    public boolean isFlagged(Pair<Integer, Integer> cellPosition) {
        return this.board.get(cellPosition).get().isFlagged();
    }

    @Override
    public long getNumberOfNeighbouringMines(Pair<Integer, Integer> position) {
        return this.board.neighbours(position).toList().stream()
                .filter((x) -> x.isPresent() )
                .map((x) -> this.board.get(x.get()))
                .filter((x) -> x.get().hasMine())
                .count();
    }

    @Override
    public void click(Pair<Integer, Integer> cellPosition) {
        this.board.get(cellPosition).get().click();
    }

    @Override
    public boolean isClicked(Pair<Integer, Integer> cellPosition) {
        return this.board.get(cellPosition).get().isClicked();
    }
}
