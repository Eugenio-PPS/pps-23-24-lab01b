package e2;

import java.util.Random;

public class LogicsImpl implements Logics {
    NeighbourList<Cell> board;

    public LogicsImpl(int size) {
        Random random = new Random();
        this.board = new NeighbourListImpl<>(
                new Pair<>(size, size),
                (p) -> java.util.Optional.of(new CellImpl(random.nextBoolean()))
        );
    }

    @Override
    public boolean hasMine(Pair<Integer, Integer> minePosition) {
        return this.board.get(minePosition).get().hasMine();
    }
}
