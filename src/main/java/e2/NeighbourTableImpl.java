package e2;

import java.util.*;
import java.util.function.Function;

public class NeighbourTableImpl<T> implements NeighbourTable<T> {
    private final List<List<Optional<T>>> storage;
    private final Pair<Integer, Integer> size;
    public NeighbourTableImpl(Pair<Integer, Integer> size) {
        this(size, (p) -> Optional.empty());
    }

    public NeighbourTableImpl(Pair<Integer, Integer> size, Function<Pair<Integer, Integer>, Optional<T>> init) {
        this.size = size;

        this.storage = new ArrayList<>();
        for(int i = 0; i < size.getY(); i++) {
            this.storage.add(new ArrayList<>());
            for(int j = 0; j < size.getX(); j++) {
                this.storage.get(i).add(init.apply(new Pair<>(j, i)));
            }
        }

    }

    @Override
    public Optional<T> get(Pair<Integer, Integer> position) {
        if(position.getX() < 0 || position.getY() < 0) {
            throw new IllegalArgumentException("Cannot access negative indexes");
        }
        return this.storage.get(position.getY()).get(position.getX());
    }

    @Override
    public void set(Pair<Integer, Integer> position, T value) {
        if(position.getX() < 0 || position.getY() < 0) {
            throw new IllegalArgumentException("Cannot access negative indexes");
        }
        this.storage.get(position.getY()).set(position.getX(), Optional.of(value));
    }

    private Optional<Pair<Integer, Integer>> positionIfInBoundsElseEmptyOptional(Pair<Integer, Integer> position) {
        if(position.getX() >= 0 &&
                position.getX() <= this.size.getX() - 1 &&
                position.getY() >= 0 &&
                position.getY() <= this.size.getY() - 1
        ) {
            return Optional.of(position);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Neighbours neighbours(Pair<Integer, Integer> position) {
        if(position.getX() >= this.size.getX() || position.getY() >= this.size.getY()) {
            throw new IndexOutOfBoundsException();
        } else if(position.getX() < 0 || position.getY() < 0) {
            throw new IllegalArgumentException();
        }
        return new Neighbours(
                positionIfInBoundsElseEmptyOptional(new Pair<>(position.getX(), position.getY() - 1)), // top
                positionIfInBoundsElseEmptyOptional(new Pair<>(position.getX() + 1, position.getY() - 1)), // top right
                positionIfInBoundsElseEmptyOptional(new Pair<>(position.getX() + 1, position.getY())), // right
                positionIfInBoundsElseEmptyOptional(new Pair<>(position.getX() + 1, position.getY() + 1)), // bottom right
                positionIfInBoundsElseEmptyOptional(new Pair<>(position.getX(), position.getY() + 1)), // bottom
                positionIfInBoundsElseEmptyOptional(new Pair<>(position.getX() - 1, position.getY() + 1)), // bottom left
                positionIfInBoundsElseEmptyOptional(new Pair<>(position.getX() - 1, position.getY())), // left
                positionIfInBoundsElseEmptyOptional(new Pair<>(position.getX() - 1, position.getY() - 1)) // top left
        );
    }

    @Override
    public Pair<Integer, Integer> size() {
        return this.size;
    }
}
