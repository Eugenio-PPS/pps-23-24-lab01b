package e1;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
public class LogicTest {
    private Optional<Pair<Integer, Integer>> getKnightPosition(Logics board, int size) {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(board.hasKnight(i, j)) {
                    return Optional.of(new Pair<>(i, j));
                }
            }
        }

        return Optional.empty();
    }
    private Optional<Pair<Integer, Integer>> getPawnPosition(Logics board, int size) {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(board.hasPawn(i, j)) {
                    return Optional.of(new Pair<>(i, j));
                }
            }
        }

        return Optional.empty();
    }

    @Test
    public void cannotCreateChessboardWithNegativeSize() {
        assertThrows(IllegalArgumentException.class, () -> new LogicsImpl(-1));
    }

    @Test
    public void cannotCreateChessboardWithZeroSize() {
        assertThrows(IllegalArgumentException.class, () -> new LogicsImpl(0));
    }
    @Test
    @Tag("To fix: should not throw StackOverflowError")
    public void oneByOneBoardThrowsStackOverflowError() {
        assertThrows(StackOverflowError.class, () -> new LogicsImpl(1));
    }

    @Test
    public void chessboardHasBothKnightAndPawn() {
        int boardSize = 2;
        Logics board = new LogicsImpl(boardSize);

        boolean pawnFound = this.getPawnPosition(board, boardSize).isPresent();
        boolean knightFound = this.getKnightPosition(board, boardSize).isPresent();

        assertTrue(pawnFound);
        assertTrue(knightFound);
    }

    @Test
    public void chessboardHasBothKnightAndPawnInDifferentPositions() {
        int boardSize = 2;
        Logics board = new LogicsImpl(boardSize);

        Pair<Integer, Integer> pawn = this.getPawnPosition(board, boardSize).get();
        Pair<Integer, Integer> knight = this.getKnightPosition(board, boardSize).get();

        assertNotEquals(pawn, knight);
    }
}
