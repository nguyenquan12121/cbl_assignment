import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

class ButtonPanel extends JPanel{
    JButton bounce,stop, reset, next;
    public ButtonPanel(){
        loadGraphics();
    }
    public void loadGraphics(){
        this.setLayout(new GridLayout(2, 2,0,0));
        //add button to the pane
        bounce = new JButton("Bounce!");
        stop = new JButton("Stop!");
        reset = new JButton("Reset!");
        next = new JButton("Next!");
        this.add(bounce);
        this.add(stop);
        this.add(reset);
        this.add(next);
    }
}