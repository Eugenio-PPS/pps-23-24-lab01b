package e2;

import java.util.Optional;
import java.util.List;

public record Neighbours (
        Optional<Pair<Integer, Integer>> top,
        Optional<Pair<Integer, Integer>> topRight,
        Optional<Pair<Integer, Integer>> right,
        Optional<Pair<Integer, Integer>> bottomRight,
        Optional<Pair<Integer,Integer>> bottom,
        Optional<Pair<Integer,Integer>> bottomLeft,
        Optional<Pair<Integer, Integer>> left,
        Optional<Pair<Integer, Integer>> topLeft
) {
    /**
     *
     * @return a list with all the neighbours in clockwise order
     */
    public List<Optional<Pair<Integer, Integer>>> toList() {
        return List.of(
                this.top,
                this.topRight,
                this.right,
                this.bottomRight,
                this.bottom,
                this.bottomLeft,
                this.left,
                this.topLeft
        );
    }
}
