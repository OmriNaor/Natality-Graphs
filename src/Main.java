import javax.swing.*;

public class Main{

    public static void main(String[] args) {

        NatalityPanel np = new NatalityPanel();

        JFrame frame = new JFrame("Chart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);

        frame.add(np);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
