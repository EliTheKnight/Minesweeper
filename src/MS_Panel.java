import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MS_Panel extends JPanel {

    private Board board;
    private boolean first, win, lose, listen, shift;
    private int row, col;

    public MS_Panel(int width, int height){
        setBounds(0, 0, width, height);
        first = false;
        lose = false;
        win = false;
        listen = true;
        shift = false;
        row = -5;
        col = -5;
        setupMouse();
        setupKeyboard();
        repaint();
    }


    public void setupKeyboard(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SHIFT){
                    shift = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SHIFT){
                    shift = false;
                }
            }
        });
    }


    public void setupMouse(){

        addMouseListener(new MouseListener() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (listen) {
                    int x = e.getX();
                    int y = e.getY();
                    col = x / 30;
                    row = y / 30;

                    if (e.getButton() == 1 && shift == false) {
                        if (first == false) {
                            int fRow = row;
                            int fCol = col;
                            board = new Board(fRow, fCol);
                            board.reveal(row, col);
                            first = true;
                            repaint();
                        } else {
                            board.reveal(row, col);
                            repaint();
                        }
                    } else if (e.getButton() == 3 || shift == true) {
                        if (first == false) {
                        } else {
                            Tile a = board.getTile(row, col);
                            a.setAppearance(2);
                            repaint();
                        }
                    }
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        if (first) {
            board.draw(g2);

            if (board.getLose()) {
                g2.setColor(Color.BLACK);
                g2.fillRect(340,335,55,20);
                g2.setColor(Color.RED);
                g2.drawString("BOOM", 350, 350);
                listen = false;
            }
            else if (board.getWin()){
                g2.setColor(Color.BLACK);
                g2.fillRect(340,335,70,20);
                g2.setColor(Color.GREEN);
                g2.drawString("YOU WIN!",345,350);
            }
        }
        else {
            for(int r = 0; r<24; r++){
                for (int c = 0; c<24; c++){
                    g2.setColor(Color.GRAY);
                        g2.drawRect(30*c,30*r,30,30);
                    }
                }
        }
    }


}
