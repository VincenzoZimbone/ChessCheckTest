public class Board {

    private boolean gameInProgress = true;
    private boolean prevTurn = false;
    private boolean whiteColor = true;
    private Piece[][] reverse=new Piece[1][2];
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

    private boolean underCheck(boolean whiteColor) throws Exception {
        boolean tempColor = !whiteColor;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = getPiece(i, j);
                if (p != null && p.isWhiteColor() == tempColor) {
                    if (!(p instanceof King) &&
                            p.checkForMove(i, j, getKingPositionX(whiteColor), getKingPositionY(whiteColor))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int getKingPositionX(boolean whiteColor) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getPiece(i, j) instanceof King) {
                    if (getPiece(i, j).isWhiteColor() == whiteColor) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    private int getKingPositionY(boolean whiteColor) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getPiece(i, j) instanceof King) {
                    if (getPiece(i, j).isWhiteColor() == whiteColor) {
                        return j;
                    }
                }
            }
        }
        return -1;
    }

    private boolean checkAlly(int prevX, int prevY, int nextX, int nextY) {

        return (pieces[nextX][nextY] != null) && (pieces[prevX][prevY].isWhiteColor() == pieces[nextX][nextY].isWhiteColor());
    }

    public void play(int prevX, int prevY, int nextX, int nextY) {
        try {
            if (getPiece(prevX, prevY) != null && getPiece(prevX, prevY).isWhiteColor() != prevTurn) {
                if (!checkAlly(prevX, prevY, nextX, nextY)) {
                    setPiece(prevX, prevY, nextX, nextY);
                    if(underCheck(whiteColor)){
                        reverseMove(prevX, prevY, nextX, nextY);
                        prevTurn = !prevTurn;
                        throw new Exception("Non puoi fare questa mossa, saresti sotto scacco");
                    }

                    //controllo checKing(prevX, prevY, nextX, nextY)
                } else {
                    throw new Exception("Mossa non valida, casella occupata da una pedina alleata.");
                }
            } else {
                throw new Exception("Non è il tuo turno!");
            }

            whiteColor = !prevTurn;
            if (underCheck(whiteColor)) {
                if (whiteColor) {
                    System.out.println("\u001B[31m" + "Il Re bianco è sotto scacco" + "\u001B[0m");
                } else {
                    System.out.println("\u001B[31m" + "Il Re nero è sotto scacco" + "\u001B[0m");
                }
            }
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e + "\u001B[0m");
        }
    }

    private void setPiece(int prevX, int prevY, int nextX, int nextY) throws Exception {
        if (pieces[prevX][prevY].checkForMove(prevX, prevY, nextX, nextY)) {
            reverse[0][0]=pieces[prevX][prevY];
            reverse[0][1]=pieces[nextX][nextY];
            pieces[nextX][nextY] = pieces[prevX][prevY];
            pieces[prevX][prevY] = null;
        } else {
            throw new Exception("Mossa non valida");
        }
        prevTurn = !prevTurn;
    }


    private void reverseMove(int prevX, int prevY, int nextX, int nextY) throws Exception{
        pieces[prevX][prevY]=reverse[0][0];
        pieces[nextX][nextY]=reverse[0][1];
    }

    public static Piece getPiece(int currentX, int currentY) {
        return pieces[currentX][currentY];
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
