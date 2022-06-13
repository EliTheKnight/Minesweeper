import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame("Mine Sweeper");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 720, 720 + 22); //(x, y, w, h) 22 due to title bar.

        MS_Panel panel = new MS_Panel(window.getWidth(), window.getHeight());

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);

        window.setVisible(true);
        window.setResizable(false);
    }
}