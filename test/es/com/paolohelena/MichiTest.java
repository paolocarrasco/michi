package es.com.paolohelena;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

public class MichiTest {
    Michi michi = new Michi();

    @Test
    public void printGridShouldRenderTheGrid() {
        String gridToPrint = michi.printGrid();
        assertEquals(gridToPrint,
                "___|___|___\n___|___|___\n___|___|___\n");
    }
    @Test
    public void setNewStateInGridWhenSetNewPositionHuman() {
        michi.setNewPositionHuman(2,1);
        michi.setNewPositionHuman(1,0);
        assertEquals(michi.printGrid(),
                "___|___|___\n_X_|___|___\n___|_X_|___\n");
    }

    @Test
    public void setNewStateInGridWhenSetNewPositionRobot() {
        michi.setNewPositionRobot();
        assertTrue(michi.printGrid().contains("_O_"));
    }

    @Test(expected = InvalidPositionException.class)
    public void throwErrorIfRenderExistingPositionInGrid() {
        michi.setNewPositionHuman(2,2);
        michi.setNewPositionHuman(2,2);
    }

    @Test(expected = FullTableException.class)
    public void setNewPositionRobotSetsPositionInAnEmptySpace() {
        michi.setNewPositionHuman(0,0);
        michi.setNewPositionHuman(1,0);
        michi.setNewPositionHuman(2,0);
        michi.setNewPositionHuman(1,1);
        michi.setNewPositionHuman(1,2);
        michi.setNewPositionHuman(2,1);
        michi.setNewPositionHuman(2,2);
        michi.setNewPositionHuman(0,2);
        michi.setNewPositionRobot();
        michi.setNewPositionRobot();

        assertEquals(1, michi.printGrid().chars().filter(ch -> ch == 'O').count());
        assertEquals(8, michi.printGrid().chars().filter(ch -> ch == 'X').count());
    }

    @Test
    public void returnFalseIfGameCanContinue() {
        michi.setNewPositionRobot();

        assertFalse(michi.isGameOver());
    }

    @Test
    public void returnTrueIfTheTableIsFull() {
        for (int i = 0; i < 9; i++) {
            michi.setNewPositionRobot();
        }

        assertTrue(michi.isGameOver());
    }

    @Test
    public void returnTrueWhenWeGotAWinnerInHorizontal1() {
        michi.setNewPositionHuman(0,0);
        michi.setNewPositionHuman(1,0);
        michi.setNewPositionHuman(2,0);

        assertTrue(michi.isGameOver());
    }

    @Test
    public void returnTrueWhenWeGotAWinnerInHorizontal2() {
        michi.setNewPositionHuman(0,1);
        michi.setNewPositionHuman(1,1);
        michi.setNewPositionHuman(2,1);

        assertTrue(michi.isGameOver());
    }

    @Test
    public void returnTrueWhenWeGotAWinnerInHorizontal3() {
        michi.setNewPositionHuman(0,2);
        michi.setNewPositionHuman(1,2);
        michi.setNewPositionHuman(2,2);

        assertTrue(michi.isGameOver());
    }

    @Test
    public void returnTrueWhenWeGotAWinnerInVertical1() {
        michi.setNewPositionHuman(0, 0);
        michi.setNewPositionHuman(0, 1);
        michi.setNewPositionHuman(0, 2);

        assertTrue(michi.isGameOver());
    }

    @Test
    public void returnTrueWhenWeGotAWinnerInVertical2() {
        michi.setNewPositionHuman(1, 0);
        michi.setNewPositionHuman(1, 1);
        michi.setNewPositionHuman(1, 2);

        assertTrue(michi.isGameOver());
    }

    @Test
    public void returnTrueWhenWeGotAWinnerInVertical3() {
        michi.setNewPositionHuman(2, 0);
        michi.setNewPositionHuman(2, 1);
        michi.setNewPositionHuman(2, 2);

        assertTrue(michi.isGameOver());
    }

    @Test
    public void returnTrueWhenWeGotAWinnerInDiagonalAscending() {
        michi.setNewPositionHuman(2, 2);
        michi.setNewPositionHuman(1, 1);
        michi.setNewPositionHuman(0, 0);

        assertTrue(michi.isGameOver());
    }

    @Test
    public void returnTrueWhenWeGotAWinnerInDiagonalDescending() {

        michi.setNewPositionHuman(2, 0);
        michi.setNewPositionHuman(1, 1);
        michi.setNewPositionHuman(0, 2);
        assertTrue(michi.isGameOver());
    }

    @Test
    public void returnTrueIfRobotWin() {
        for (int i = 0; i < 7; i++) {
            michi.setNewPositionRobot();
        }

        assertTrue(michi.isGameOver());
    }

    @Test
    public void showRobotAsWinnerIfTheyWon() {
        for (int i = 0; i < 7; i++) {
            michi.setNewPositionRobot();
        }

        assertEquals("Congrats to the best of all the artificial intelligences, it deserves its throne", michi.printWinner());
    }

    @Test
    public void showHumanAsWinnerIfTheyWon() {
        michi.setNewPositionHuman(0,1);
        michi.setNewPositionHuman(1,1);
        michi.setNewPositionHuman(2,1);

        assertEquals("Congrats to the human, you are so fuck$%$ awesome", michi.printWinner());
    }
    @Test
    public void showHumanAndRobotAsTie() {
        michi.setNewPositionHuman(0,1);
        michi.setNewPositionHuman(0,2);
        michi.setNewPositionHuman(1,0);
        michi.setNewPositionHuman(2,2);
        for (int i = 0; i < 5; i++) {
            michi.setNewPositionRobot();
        }

        assertEquals("Both are genius, try again", michi.printWinner());
    }

}
