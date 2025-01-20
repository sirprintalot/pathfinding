import java.awt.Component;
import javax.swing.JFrame;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(3);
        window.setResizable(false);
        window.add(new Panel());
        window.pack();
        window.setLocationRelativeTo((Component)null);
        window.setVisible(true);
    }
}          
