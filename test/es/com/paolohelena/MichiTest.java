package es.com.paolohelena;

import org.junit.Test;
import static org.junit.Assert.*;

public class MichiTest {
    Michi michi = new Michi();
    @Test
    public void printGridShouldRenderTheGrid() {
        String gridToPrint = michi.printGrid();
        assertEquals(gridToPrint,
                " ___|___|___ \n ___|___|___ \n    |   |   ");
    }
    @Test
    public void ReturnRenderPositionWhenSendXandY() {
        michi.setNewPositionHuman(2,1);
        String gridToPrint = michi.printGrid();
        assertEquals(gridToPrint,
                " ___|___|___ \n ___|___|___ \n    | X |   ");
    }


}
