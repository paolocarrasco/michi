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
            String human = "X";
            String robotSymbol = "O";
            return determineIfWinner(human) || determineIfWinner(robotSymbol);
        }
    }

    private boolean determineIfWinner(String playerSymbol) {
        for (int y = 0; y < 3; y++) {
            var hasWinGameHorizontalHuman = (michiGame[0][y] == playerSymbol && michiGame[1][y] == playerSymbol && michiGame[2][y] == playerSymbol);
            var hasWinGameVerticalHuman = (michiGame[y][0] == playerSymbol && michiGame[y][1] == playerSymbol && michiGame[y][2] == playerSymbol);

            if (hasWinGameHorizontalHuman||hasWinGameVerticalHuman){
                return true;
            }
        }
        var diagonalAscending = michiGame[2][2] == playerSymbol && michiGame[1][1] == playerSymbol && michiGame[0][0] == playerSymbol;
        var diagonalDescending = michiGame[2][0] == playerSymbol && michiGame[1][1] == playerSymbol && michiGame[0][2] == playerSymbol;
        return diagonalAscending || diagonalDescending;
    }

    public String printWinner() {
        if(determineIfWinner("X")){
            return "Congrats to the human, you are so fuck$%$ awesome";
        }else if (determineIfWinner("O")) {
            return "Congrats to the best of all the artificial intelligences, it deserves its throne";
        }
        return "Both are genius, try again";
    }
}
