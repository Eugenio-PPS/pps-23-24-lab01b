package e2;

import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class NeighboursTest {
    @Test
    public void leftWorks() {
        Pair<Integer, Integer> p = new Pair<>(1,1);
        Neighbours neighbours = new Neighbours(Optional.of(p), Optional.empty(), Optional.empty(), Optional.empty());
        assertEquals(p, neighbours.getLeft().get());
    }

    @Test
    public void topWorks() {
        Pair<Integer, Integer> p = new Pair<>(1,1);
        Neighbours neighbours = new Neighbours(Optional.empty(), Optional.of(p), Optional.empty(), Optional.empty());
        assertEquals(p, neighbours.getTop().get());
    }

    @Test
    public void bottomWorks() {
        Pair<Integer, Integer> p = new Pair<>(1,1);
        Neighbours neighbours = new Neighbours(Optional.empty(), Optional.empty(), Optional.of(p), Optional.empty());
        assertEquals(p, neighbours.getBottom().get());
    }

    @Test
    public void rightWorks() {
        Pair<Integer, Integer> p = new Pair<>(1,1);
        Neighbours neighbours = new Neighbours(Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(p));
        assertEquals(p, neighbours.getRight().get());
    }


}
