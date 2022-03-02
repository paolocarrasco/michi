package es.com.paolohelena;

public class Michi {

    private String[][] michiGame = new String[3][3];

    public String printGrid() {
        String grid = " ";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (michiGame[i][j] == null) {
                    grid += "___|";
                }else{
                    grid += "_x_|";
                }

            }
        }
        return grid;
    }

    public void setNewPositionHuman(int x, int y) {
        michiGame[2][1] = "X";


    }
}
