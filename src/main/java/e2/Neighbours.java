package e2;

import java.util.Collection;
import java.util.Optional;

public class Neighbours {
    private final Optional<Pair<Integer, Integer>> left;
    private final Optional<Pair<Integer, Integer>> top;
    private final Optional<Pair<Integer, Integer>> bottom;
    private final Optional<Pair<Integer, Integer>> right;

    public Neighbours(
            Optional<Pair<Integer, Integer>> left,
            Optional<Pair<Integer, Integer>> top,
            Optional<Pair<Integer, Integer>> bottom,
            Optional<Pair<Integer, Integer>> right
    ) {
        this.left = left;
        this.top = top;
        this.bottom = bottom;
        this.right = right;
    }

    public Optional<Pair<Integer, Integer>> getLeft() {
        return this.left;
    }

    public Optional<Pair<Integer, Integer>> getTop() {
        return this.top;
    }

    public Optional<Pair<Integer, Integer>> getBottom() {
        return this.bottom;
    }

    public Optional<Pair<Integer, Integer>> getRight() {
        return this.right;
    }
}
