import java.awt.*;

public class Board {

    private Tile[][] board;
    private static int count;
    private int flags;
    private boolean win, lose;

    public Board(int row, int col){
        board = new Tile[24][24];
        flags = 0;
        win = false;
        lose = false;
        for (int i = 0; i<41; i++){
            int r = (int)(Math.random()*24);
            int c = (int)(Math.random()*24);

            if (board[r][c] == null && !(r-1 <= row && r+1 >= row && c-1 <= col && c+1 >= col)){
                board[r][c] = new Tile(-1);
                i--;
            }
//            if(board[r][c] != null && board[r][c].getVal() == -1){
//            }
//            else if(r-1 <= row && r+1 >= row){
//                if (c-1 <= col && c+1 >= col){
//                }
//            }
//            else{
//                board[r][c] = new Tile(-1);
//                i--;
//            }
        }
        for (int r = 0; r<24; r++){
            for (int c = 0; c<24; c++){
                if (board[r][c] != null && board[r][c].getVal() == -1){}
                else {
                    board[r][c] = new Tile(flagsSurrounding(r,c));
                }
            }
        }

        for (int r = 0; r<24; r++){
            for (int c = 0; c<24; c++){
                if (board[r][c].getVal() == -1)
                    flags++;
            }
        }
        count = 0;
    }

    public int flagsSurrounding(int r, int c){
        int count = 0;
        try{
            if(board[r-1][c].getVal() == -1){
                count ++;
            }
        }
        catch (Exception e){}
        try{
            if(board[r+1][c].getVal() == -1){
                count ++;
            }
        }
        catch (Exception e){}
        try{
            if(board[r][c-1].getVal() == -1){
                count ++;
            }
        }
        catch (Exception e){}
        try{
            if(board[r][c+1].getVal() == -1){
                count ++;
            }
        }
        catch (Exception e){}
        try{
            if(board[r-1][c-1].getVal() == -1){
                count ++;
            }
        }
        catch (Exception e){}
        try{
            if(board[r-1][c+1].getVal() == -1){
                count ++;
            }
        }
        catch (Exception e){}
        try{
            if(board[r+1][c-1].getVal() == -1){
                count ++;
            }
        }
        catch (Exception e){}
        try{
            if(board[r+1][c+1].getVal() == -1){
                count ++;
            }
        }
        catch (Exception e){}

        return count;
    }

    public void reveal(int row, int col){

        //checks that values are assigned correctly:

//        for (Tile[] aod:board){
//            for (Tile jed:aod){
//                System.out.print(jed.getAppearance());
//            }
//            System.out.println();
//        }


        if (board[row][col].getAppearance() == 0) {
            board[row][col].setAppearance(1);
            count++;

           if (board[row][col].getVal() == -1){
               lose = true;
           }


           if (board[row][col].getVal() == 0) {
                if (row == 0) {
                    if (col == 0) {
                        reveal(row + 1, col);
                        reveal(row, col + 1);
                        reveal(row + 1, col + 1);
                    } else if (col == 23) {
                        reveal(row + 1, col);
                        reveal(row, col - 1);
                        reveal(row + 1, col - 1);
                    } else {
                        reveal(row + 1, col);
                        reveal(row, col + 1);
                        reveal(row + 1, col + 1);
                        reveal(row, col - 1);
                        reveal(row + 1, col - 1);
                    }
                } else if (row == 23) {
                    if (col == 0) {
                        reveal(row - 1, col);
                        reveal(row, col + 1);
                        reveal(row - 1, col + 1);
                    } else if (col == 23) {
                        reveal(row - 1, col);
                        reveal(row, col - 1);
                        reveal(row - 1, col - 1);
                    } else {
                        reveal(row - 1, col);
                        reveal(row, col + 1);
                        reveal(row - 1, col + 1);
                        reveal(row - 1, col - 1);
                        reveal(row, col - 1);
                    }
                } else {
                    if (col == 0) {
                        reveal(row, col + 1);
                        reveal(row - 1, col + 1);
                        reveal(row + 1, col + 1);
                        reveal(row - 1, col);
                        reveal(row + 1, col);
                    } else if (col == 23) {
                        reveal(row - 1, col);
                        reveal(row + 1, col);
                        reveal(row - 1, col - 1);
                        reveal(row, col - 1);
                        reveal(row + 1, col - 1);
                    } else {
                        reveal(row - 1, col);
                        reveal(row, col + 1);
                        reveal(row - 1, col + 1);
                        reveal(row - 1, col - 1);
                        reveal(row, col - 1);
                        reveal(row + 1, col);
                        reveal(row + 1, col + 1);
                        reveal(row + 1, col - 1);
                    }

                    if(count + flags >= 576){
                        win = true;
                    }
                }
            }
        }
        else if (board[row][col].getAppearance() == 2){
            board[row][col].setAppearance(0);
        }
    }

    public void draw(Graphics2D g2){

        for(int r = 0; r<24; r++){
            for (int c = 0; c<24; c++){
                g2.setColor(Color.GRAY);
                if (board[r][c].getAppearance() == 0)
                    g2.drawRect(30*c,30*r,30,30);
                else if(board[r][c].getAppearance() == 1){
                    g2.drawRect(30*c,30*r,30,30);
                    g2.setColor(Color.BLACK);
                    if (board[r][c].getVal() == -1) {
                        g2.setColor(Color.RED);
                        g2.fillRect(30*c,30*r,30,30);
                    }
                    else {
                        g2.drawString("" + board[r][c].getVal(), 30 * c + 10, 30 * r + 20);
                    }
                }
                else if(board[r][c].getAppearance() == 2){
                    g2.setColor(Color.GRAY);
                    g2.drawRect(30*c,30*r,30,30);
                    g2.setColor(Color.RED);
                    g2.drawString("F",30*c+10,30*r+20);
                }
            }
        }
    }

    public Tile getTile(int r, int c){return board[r][c];}

    public boolean getWin() { return win; }

    public boolean getLose() { return lose; }

    public boolean isValid(int r, int c){
        if (r<24 && r>=0 && c<24 && c>=0){ return true;}
        return false;
    }

}
