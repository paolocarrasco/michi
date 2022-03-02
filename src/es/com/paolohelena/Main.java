package es.com.paolohelena;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Michi michi = new Michi();
        michi.setNewPositionHuman(1,0);
        String grid = michi.printGrid();
        System.out.println(grid);

    }
}
