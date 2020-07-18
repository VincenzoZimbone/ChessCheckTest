import Pieces.*;


public class Board {

    //salvataggio board
    Piece[][] pieces = new Piece[8][8];

    public Board(){
        fillBoard();
    }

    Piece[][] fillBoard(){

        //Kings
        pieces[0][4] = new King(false);
        pieces[7][4] = new King(true);

        //Queens
        pieces[0][3] = new Queen(false);
        pieces[7][3] = new Queen(true);

        //Rooks
        for (int i = 0; i <= 7; i +=7){
            pieces[0][i] = new Rook(false);
            pieces[7][i] = new Rook(true);
        }

        //Knights
        for (int i = 1; i <= 6; i += 5) {
            pieces[0][i] = new Knight(false);
            pieces[7][i] = new Knight( true);
        }

        //Bishops
        for (int i = 2; i <= 5; i += 3) {
            pieces[0][i] = new Bishop(false);
            pieces[7][i] = new Bishop(true);
        }

        for (int i = 0; i < 8; i++) {
            pieces[1][i] = new Pawn( false);
            pieces[6][i] = new Pawn( true);
        }

        return pieces;
    }

    @Override
    public String toString() {
        String result = "";
        int numbers = 8;
        for (int i = 0; i < 8; i++){
            result+="\n"+ numbers + " ";
            numbers--;
            for (int j= 0; j < 8; j++){
                if (this.pieces[i][j]==null){
                    result+="[\t\t]";
                }else{
                    result+="[\t"+this.pieces[i][j]+"\t]";
                }
            }
        }
        result += " " + "\n \tA\t \tB\t \tC\t \tD\t \tE\t \tF\t \tG\t \tH";
        return result;
    }
}
