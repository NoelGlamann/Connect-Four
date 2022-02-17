package edu.neumont.csc150;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameController {
    private ConsoleInterface ci = new ConsoleInterface();
    private ArrayList<Player> players = new ArrayList<>();
    private Random r = new Random();
    private Board board = new Board();

    public void run() throws IOException, InterruptedException {
        while(true){
            board.boardArray = board.resetBoard();
            players.clear();
            ci.displayMainMenu();
            int selection = ci.getUserInputAsInt(1,4);
            switch (selection) {
                case 1 -> {
                    playerSetUp(true, true, false);
                }
                case 2 -> {
                    playerSetUp(true, false, false);
                }
                case 3 -> {
                    playerSetUp(false, false, true);
                }
                case 4 -> {
                    return;
                }
                default -> throw new IllegalArgumentException("unknown menu item\n");
            }
        }
    }

    private void playerSetUp(boolean p1IsHuman, boolean p2IsHuman, boolean timer) throws IOException, InterruptedException {
        if (p1IsHuman){
            String pAName = ci.getHumanName();
            Player pA = new Human(pAName, true);
            players.add(pA);
        } else {
            Player pA = new Computer("Computer", false);
            players.add(pA);
        }
        if (p2IsHuman){
            String pBName = ci.getHumanName();
            Player pB = new Human(pBName, true);
            players.add(pB);
        }else if (players.get(0).getName() == "Computer"){
            Player pB = new Computer("Computer2", false);
            players.add(pB);
        }else{
            Player pB = new Computer("Computer", false);
            players.add(pB);
        }
        playerOrder(timer);
    }
    private void playerOrder(boolean timer) throws InterruptedException, IOException {
        System.out.print("Player one will be chosen randomly.");
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            System.out.print(".");
        }
        int player2;
        int player1 = r.nextInt(0,2);
        if (player1 == 0){player2 = 1;}
        else{player2 =0;}
        players.get(player1).setPlayerOrder(1);
        players.get(player1).setMyColor(Piece.y);
        System.out.println(players.get(player1).getName() + " is first!");
        players.get(player2).setPlayerOrder(2);
        players.get(player2).setMyColor(Piece.r);

        playGame(players.get(player1), players.get(player2), timer);
    }
    private void playGame(Player p1, Player p2, boolean timer) throws IOException, InterruptedException {
        boolean win = false;
        boolean player1Turn = true;
        int columnSelect;
        while (!win){
            if (timer) {
                Thread.sleep(1000);
            }
            ci.displayBoard(board.boardArray);
            if (player1Turn){
                player1Turn = false;
                ci.displayPlayerTurn(p1);
                columnSelect = getColumn(p1);
                playPiece(p1, columnSelect);
                win = board.checkForWin(p1.getMyColor(), columnSelect);
            }else{
                player1Turn = true;
                ci.displayPlayerTurn(p2);
                columnSelect = getColumn(p2);
                playPiece(p2, columnSelect);
                win = board.checkForWin(p2.getMyColor(), columnSelect);
            }
        }
        ci.displayBoard(board.boardArray);
        if(!player1Turn){
            ci.displayWin(p1);
        }else{
            ci.displayWin(p2);
        }
        System.out.println(Console.RESET);
        Thread.sleep(1500);
    }
    private int getColumn(Player p) throws IOException {
        int chosenColumn;
        if (p.isHuman()){
            chosenColumn = ci.getUserInputAsInt(1,7);
        } else{
            chosenColumn = r.nextInt(1,8);
        }
        return chosenColumn;
    }
    private void playPiece(Player p, int column) throws IOException {
        boolean done = false;
        while(!done){
            boolean columnAvailable = board.columnAvailable(column);
            if (!columnAvailable){
                boolean allFull = true;
                for (Piece piece: board.boardArray[0]){
                    if (piece == Piece.e){
                        allFull = false;
                    }
                }
                if (allFull){
                    System.out.println("It's a tie!");
                    break;
                }
                System.out.println("Please select another column: ");
                column = getColumn(p);
            }else{
                int row = board.rowAvailable(column);
                board.playPiece(p, column, row);
                done = true;
            }
        }
    }

}
