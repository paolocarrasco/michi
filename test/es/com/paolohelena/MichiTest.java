package es.com.paolohelena;

import org.junit.Test;

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
}
