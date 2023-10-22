import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

class PlayPane extends JLayeredPane{
    public PlayPane(ThermostatPanel tp, PlayPanel pp){
        super();
        JLabel l1 = new JLabel();
        l1.setOpaque(true);
        l1.setBackground(Color.RED);
        l1.setBounds(50, 50, 200, 200);
        // tp.setOpaque(true);
        // pp.setOpaque(true);
        this.add(tp);
        this.add(pp);
        // this.add(l1, Integer.valueOf(2));
    }
}