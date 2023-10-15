import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

class MenuPanel extends JPanel implements ActionListener {
    Timer timer; 
    JButton bounce;
    JButton stop; 
    JButton reset;
    JLabel force;
    long startTime = -1l;
    long endTime = -1l;
    private GridLayout gl;
    public MenuPanel(){
        timer = new Timer(10, this);
        timer.start();
        JPanel buttonPanel=new JPanel();
        //create panel to hold 2 buttons
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        //add button to the pane
        bounce = new JButton("Bounce!");
        stop = new JButton("Stop!");
        reset = new JButton("Reset!");
        buttonPanel.add(bounce);
        buttonPanel.add(stop);
        buttonPanel.add(reset);
        buttonPanel.setBackground(Color.red);


        //Main left panel to hold buttons and text
        //Set text field
        force = new JLabel("0.00");


        force.setBackground(Color.green);
        //Center the text
        force.setHorizontalAlignment(JLabel.CENTER);        
        this.setLayout(new BorderLayout());        
        this.add(buttonPanel, BorderLayout.NORTH);
        this.add(force, BorderLayout.CENTER);
    }
    public void startAnimation(PlayPanel pp){
        this.bounce.addMouseListener(
            // I wanted to use MouseAction but it doesnt work
            new MouseListener() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent arg0) {
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent arg0) {
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent arg0) {
                }

                @Override
                public void mousePressed(java.awt.event.MouseEvent arg0) {
                    startTime = System.currentTimeMillis();
                    timer.start();
                }

                @Override
                public void mouseReleased(java.awt.event.MouseEvent arg0) {
                    endTime = System.currentTimeMillis();
                    long duration = endTime - startTime;
                    System.out.println(duration);
                    pp.setTimer(true, duration);
                }
            }

        );
        this.stop.addActionListener(e -> pp.setTimer(false, 0l));
    }

    public void reset(PlayPanel pp){
        this.reset.addActionListener( e ->{
            startTime = -1l;
            endTime = -1l;           
            force.setText("0.00");
            timer.stop();
            pp.reset();
        }
        );
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (startTime != -1l){
        long display = System.currentTimeMillis() - startTime;
        force.setText(Long.toString(display));
        repaint();
        }
        if (endTime !=-1l){
            timer.stop();
        }
    }
}