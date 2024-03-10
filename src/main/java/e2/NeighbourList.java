package e2;

import java.util.Collection;
import java.util.Optional;

// This should/could extend Collection
public interface NeighbourList<T> {
    Optional<T> get(Pair<Integer, Integer> position);

    void set(Pair<Integer, Integer> position, T value);

    Neighbours neighbours(Pair<Integer, Integer> position);

    Pair<Integer, Integer> size();
}
