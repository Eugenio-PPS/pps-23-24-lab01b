package e2;

import java.util.*;

public class NeighbourListImpl<T> implements NeighbourList<T>{
    private List<List<Optional<T>>> storage;
    private Pair<Integer, Integer> size;
    public NeighbourListImpl(Pair<Integer, Integer> size) {
        this.size = size;

        this.storage = new ArrayList<>();
        for(int i = 0; i < size.getY(); i++) {
            this.storage.add(new ArrayList<>());
            for(int j = 0; j < size.getX(); j++) {
                this.storage.get(i).add(Optional.empty());
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

    private Optional<Pair<Integer, Integer>> leftNeighbour(Pair<Integer, Integer> position) {
        if(position.getX() > 0) {
            return Optional.of(new Pair<>(position.getX() - 1, position.getY()));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Pair<Integer, Integer>> rightNeighbour(Pair<Integer, Integer> position) {
        if(position.getX() < this.size.getX() - 1) {
            return Optional.of(new Pair<>(position.getX() + 1, position.getY()));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Pair<Integer, Integer>> bottomNeighbour(Pair<Integer, Integer> position) {
        if(position.getY() < this.size.getY() - 1) {
            return Optional.of(new Pair<>(position.getX(), position.getY() + 1));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Pair<Integer, Integer>> topNeighbour(Pair<Integer, Integer> position) {
        if(position.getY() > 0) {
            return Optional.of(new Pair<>(position.getX(), position.getY() - 1));
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
                this.leftNeighbour(position),
                this.topNeighbour(position),
                this.bottomNeighbour(position),
                this.rightNeighbour(position)
        );
    }
}
