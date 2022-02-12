package edu.neumont.csc150;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInterface {
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public void displayMainMenu(){
        System.out.println("\n\nHow would you like to play?\r\n" +
                "\t1 - Human vs Human\r\n" +
                "\t2 - Human vs Computer\r\n" +
                "\t3 - Computer vs Computer\r\n" +
                "\t4 - Exit\r\n");
    }
    public void displayBoard(Piece[][] board){
        loopByRow(board);
    }
    public void displayPlayerTurn(Player p){
        System.out.println("Choose a column (1-7) "+p.getName()+" ("+(p.isHuman() == false? "Computer":"Human")+" - "+p.getMyColor()+")");
    }
    public void displayWin(Player p){
        System.out.print(Console.PURPLE);
        System.out.println("-----------------");
        System.out.print(Console.CYAN);
        System.out.println("\t"+p.getName()+" won!");
        System.out.print(Console.PURPLE);
        System.out.println("-----------------");
        System.out.print(Console.WHITE);
    }

    public int getUserInputAsInt(int min, int max) throws IOException {
        float value = getUserInputAsFloat(min, max);

        return (int) value;
    }
    public float getUserInputAsFloat(float min, float max) throws IOException {
        float input = 0;
        do {
            String line = in.readLine();
            try {
                input = Float.parseFloat(line);
                if (input < min || input > max) {
                    throw new NumberFormatException("out of range");
                }
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Input invalid. " +
                        "You must enter a number between " + min + " and " + max);
            }
        } while (true);
        return input;
    }
    private void loopByRow(Piece[][] board){
        for(Piece[] row: board){
            int columnIndex = 0;
            int numberPlace = 1;
            for (Piece p: row){
                if (row == board[6]){
                    System.out.print("   " +(p == Piece.n? numberPlace : p)+ "  ");
                    numberPlace++;
                }else{System.out.print(Console.BLUE);
                    System.out.print("|  ");
                    if(p == Piece.y){
                        System.out.print(Console.YELLOW);
                    }else{
                        System.out.print(Console.RED);
                    }
                    System.out.print(p == Piece.e? " " : p);
                    if (columnIndex == 6){
                        System.out.print(Console.BLUE);
                        System.out.print("  |");
                    }else{
                        System.out.print("  ");
                    }
                    columnIndex++;
                }
            }
            System.out.print(Console.RESET);
            System.out.println();
        }
    }
    public String getHumanName() throws IOException {
        System.out.print("Player Name: ");
        return in.readLine();
    }

}
