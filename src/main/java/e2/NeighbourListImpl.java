package e2;

import e1.Pair;

import java.util.*;

public class NeighbourListImpl<T> implements NeighbourList<T>{
    List<List<Optional<T>>> storage;
    public NeighbourListImpl(Pair<Integer, Integer> size) {
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
}
