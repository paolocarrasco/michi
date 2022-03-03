package es.com.paolohelena;

import java.util.Random;

public class Michi {

    private String[][] michiGame = new String[3][3];
    private int count = 0;

    public String printGrid() {
        String grid = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (michiGame[i][j] == null) {
                    grid += "___|";
                } else {
                    grid += "_" + michiGame[i][j] + "_|";
                }

            }
            grid = grid.replaceAll("(\\|)$", "");
            grid += "\n";
        }
        return grid;
    }

    public void setNewPositionHuman(int x, int y) {
        if (michiGame[x][y] == null) {
            michiGame[x][y] = "X";
            this.count++;
        }
        else {
            throw new InvalidPositionException();
        }
    }

    public void setNewPositionRobot() {
        int x = new Random().nextInt(3);
        int y = new Random().nextInt(3);
        if (michiGame[x][y] == null) {
            michiGame[x][y] = "O";
            this.count++;
        }else{
            if (this.count < 9){
                setNewPositionRobot();
            } else {
                throw new FullTableException();
            }

        }
    }
}
