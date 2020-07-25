public class Board {

    private boolean gameInProgress = true;
    private boolean whiteTurn = true;
    private boolean prevTurn = false;

    //salvataggio board
    private static final Piece[][] pieces = new Piece[8][8];

    public Board() {
        fillBoard();
    }


    private void fillBoard() {

        //Kings
        pieces[0][4] = new King(false);
        pieces[7][4] = new King(true);

        //Queens
        pieces[0][3] = new Queen(false);
        pieces[7][3] = new Queen(true);

        //Rooks
        for (int i = 0; i <= 7; i += 7) {
            pieces[0][i] = new Rook(false);
            pieces[7][i] = new Rook(true);
        }

        //Knights
        for (int i = 1; i <= 6; i += 5) {
            pieces[0][i] = new Knight(false);
            pieces[7][i] = new Knight(true);
        }

        //Bishops
        for (int i = 2; i <= 5; i += 3) {
            pieces[0][i] = new Bishop(false);
            pieces[7][i] = new Bishop(true);
        }

        //Pawns
        for (int i = 0; i < 8; i++) {
            pieces[1][i] = new Pawn(false);
            pieces[6][i] = new Pawn(true);
        }

    }

    private boolean checkAlly(int prevX, int prevY, int nextX, int nextY) {

        return (pieces[nextX][nextY] != null) && (pieces[prevX][prevY].isWhiteColor() == pieces[nextX][nextY].isWhiteColor());
    }

    public static Piece getPiece(int currentX, int currentY) {
        return pieces[currentX][currentY];
    }

    public void setPiece(int prevX, int prevY, int nextX, int nextY) {
        try {
            if (getPiece(prevX, prevY) != null && getPiece(prevX, prevY).isWhiteColor() != prevTurn) {
                if (!checkAlly(prevX, prevY, nextX, nextY)) {
                    if (pieces[prevX][prevY].checkForMove(prevX, prevY, nextX, nextY)) {
                        pieces[nextX][nextY] = pieces[prevX][prevY];
                        pieces[prevX][prevY] = null;
                    } else {
                        throw new Exception("Mossa non valida");
                    }
                    prevTurn = !prevTurn;
                } else {
                    throw new Exception("Mossa non valida, casella occupata da una pedina alleata.");
                }
            } else {
                throw new Exception("Non è il tuo turno!");
            }
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e + "\u001B[0m");
        }
    }

    public boolean isGameInProgress() {
        return gameInProgress;
    }

    public void setGameInProgress(boolean gameInProgress) {
        this.gameInProgress = gameInProgress;
    }

    @Override
    public String toString() {
        String result = "";
        int numbers = 8;
        for (int i = 0; i < 8; i++) {
            result += "\n" + numbers + " ";
            numbers--;
            for (int j = 0; j < 8; j++) {
                if (this.pieces[i][j] == null) {
                    result += "[\t\t]";
                } else {
                    result += "[\t" + this.pieces[i][j] + "\t]";
                }
            }
        }
        result += " " + "\n \tA\t \tB\t \tC\t \tD\t \tE\t \tF\t \tG\t \tH";
        return result;
    }
}
