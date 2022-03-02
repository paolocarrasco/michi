package es.com.paolohelena;

import java.util.Random;

public class Michi {

    private String[][] michiGame = new String[3][3];

    public String printGrid() {
        String grid = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (michiGame[i][j] == null) {

                    grid += "___|";
                }else{
                    grid +="_"+michiGame[i][j]+"_|";


                }

            }
            grid = grid.replaceAll("(\\|)$","");
            grid += "\n";
        }
        return grid;
    }

    public void setNewPositionHuman(int x, int y) {
        michiGame[x][y] = "X";


    }

    public void setNewPositionRobot() {
        int x = new Random().nextInt(3);
        int y = new Random().nextInt(3);
        michiGame[x][y] = "O";
    }
}
