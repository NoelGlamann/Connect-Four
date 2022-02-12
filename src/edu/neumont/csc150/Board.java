package edu.neumont.csc150;

public class Board {
    Piece[][] boardArray = {
            {Piece.e, Piece.e, Piece.e, Piece.e, Piece.e, Piece.e, Piece.e},
            {Piece.e, Piece.e, Piece.e, Piece.e, Piece.e, Piece.e, Piece.e},
            {Piece.e, Piece.e, Piece.e, Piece.e, Piece.e, Piece.e, Piece.e},
            {Piece.e, Piece.e, Piece.e, Piece.e, Piece.e, Piece.e, Piece.e},
            {Piece.e, Piece.e, Piece.e, Piece.e, Piece.e, Piece.e, Piece.e},
            {Piece.e, Piece.e, Piece.e, Piece.e, Piece.e, Piece.e, Piece.e},
            {Piece.n, Piece.n, Piece.n, Piece.n, Piece.n, Piece.n, Piece.n},
    };
    public boolean columnAvailable(int column){
        boolean available = false;
        if (boardArray[0][column-1] == Piece.e){
            available = true;
        }
        return available;
    }
    public int rowAvailable(int column) {
        int rowIndex = 0;
        for(Piece[] row: boardArray){
            if (row[column-1] != Piece.e){
                break;
            }
            rowIndex++;
        }
        return rowIndex;
    }

    public void playPiece(Player p, int column, int row){
        Piece pieceColor = p.getMyColor();
        boardArray[row-1][column-1] = pieceColor;
    }

    public boolean checkForWin(Piece color){
        boolean won = checkVertical(color);
        if(!won){
            won = checkHorizontal(color);
        }
        if(!won){
            won = checkDiagonal(color);
        }
        return won;
    }

    public boolean checkVertical(Piece color){
        boolean won = false;
        for(Piece[] row: boardArray) {
            int count = 0;
            for (Piece p : row) {
                boolean looping = true;
                if (p == color){
                    Piece[] rowNum = row;
                   while (looping){
                       count++;
                       if (boardArray[rowNum][p]){

                       }
                   }

                }
            }
        }
        return won;
    }
    public boolean checkHorizontal(Piece color){
        boolean won = false;
        for(Piece[] row: boardArray) {
            int count = 0;
            for (Piece p : row) {
                if (p == color){
                    count++;
                    if (count == 4){
                        won = true;
                    }
                }else{
                    count=0;
                }
            }
        }
        return won;
    }
    public boolean checkDiagonal(Piece color){
        boolean won = false;
        return won;
    }

}
