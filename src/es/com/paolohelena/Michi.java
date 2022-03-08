package es.com.paolohelena;

import java.util.Random;

public class Michi {

    private String[][] michiGame = new String[3][3];
    private int count = 0;

    public String printGrid() {
        var grid = "";
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
        var x = new Random().nextInt(3);
        var y = new Random().nextInt(3);
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

    public boolean isGameOver() {
        if (this.count == 9) {
            return true;
        } else {
            for (int y = 0; y < 3; y++) {
                var hasWinGameHorizontalHuman = (michiGame[0][y] == "X" && michiGame[1][y] == "X" && michiGame[2][y] == "X");
                var hasWinGameHorizontalRobot = (michiGame[0][y] == "O" && michiGame[1][y] == "O" && michiGame[2][y] == "0");
                var hasWinGameVerticalHuman = (michiGame[y][0] == "X" && michiGame[y][1] == "X" && michiGame[y][2] == "X");
                var hasWinGameVerticalRobot = (michiGame[y][0] == "O" && michiGame[y][1] == "O" && michiGame[y][2] == "O");

                if (hasWinGameHorizontalHuman||hasWinGameVerticalHuman|| hasWinGameHorizontalRobot||hasWinGameVerticalRobot){
                    return true;
                }
            }
            var diagonalAscending = michiGame[2][2] == "X" && michiGame[1][1] == "X" && michiGame[0][0] == "X";
            var diagonalDescending = michiGame[2][0] == "X" && michiGame[1][1] == "X" && michiGame[0][2] == "X";
            var diagonalAscendingRobot = michiGame[2][2] == "O" && michiGame[1][1] == "O" && michiGame[0][0] == "O";
            var diagonalDescendingRobot = michiGame[2][0] == "O" && michiGame[1][1] == "O" && michiGame[0][2] == "O";
            return diagonalAscending || diagonalDescending || diagonalAscendingRobot || diagonalDescendingRobot;
        }
    }
}
