package es.com.paolohelena;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Michi michi = new Michi();
        michi.setNewPositionHuman(1,0);;
        String grid = michi.printGrid();
        System.out.println(grid);
        michi.setNewPositionRobot();
        grid = michi.printGrid();
        System.out.println(grid);
        /// research on how to receive input from command line

    }
}
