package Game;

import Common.GameBoard.Board;
import javafx.scene.Group;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTester {
    private Group root = new Group();

    @Test
    public void EmptyTest() {
        assertEquals(0, 0);
    }

    @Test
    public void aBoardShouldHaveTheCorrectNumberOfLines() throws IOException {
        Board testBoard = new Board(root);
        assertEquals(testBoard.getLines().size(), testBoard.getNB_LINES());
    }

    @Test
    public void aLineContainedInBoardShouldHaveACorrectId() throws IOException {
        Board testBoard = new Board(root);
        for(int indexCheck = 0; indexCheck < testBoard.getNB_LINES(); ++indexCheck)
        {
            assertEquals(testBoard.getLines().get(indexCheck).getNoLine(), indexCheck + 1);
        }
    }
}
