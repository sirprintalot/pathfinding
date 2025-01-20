import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Node extends JButton implements ActionListener {
    Node parent;
    int col;
    int row;
    int gCost;
    int hCost;
    int fCost;
    boolean start;
    boolean goal;
    boolean solid;
    boolean open;
    boolean checked;

    public Node(int col, int row) {
        this.col = col;
        this.row = row;
        this.setBackground(Color.white);
        this.setForeground(Color.BLACK);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        this.setBackground(Color.BLUE);
        this.repaint();
    }

    public void setAsStart() {
        this.setBackground(Color.CYAN);
        this.setForeground(Color.BLACK);
        this.setText("START");
        this.start = true;
    }

    public void setAsGoal() {
        this.setBackground(Color.GREEN);
        this.setForeground(Color.BLACK);
        this.setText("GOAL");
        this.goal = true;
    }

    public void setAsSolid() {
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.BLACK);
        this.setText("Solid");
        this.solid = true;
    }

    public void setAsOpen(){
        open = true;
    }

    public void setAsChecked(){
        if(!start && !goal){
             setBackground(Color.ORANGE);
             setForeground(Color.BLACK);
        }

        checked = true;
    }

     public void setAsPath(){
        setBackground(Color.green);
        setForeground(Color.BLACK);
     }






}
