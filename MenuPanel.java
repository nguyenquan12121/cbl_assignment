import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

class MenuPanel extends JPanel {
    ButtonPanel bp;
    InformationPanel ip;
    int score=0;
    long display;
    PlayPanel ppMain;
    long startTime = -1l;
    Double secondsLeft;
    public MenuPanel(PlayPanel pp){
        this.ppMain = pp;
        ip = new InformationPanel();
        bp = new ButtonPanel();
        this.ppMain.addInfoPanel(ip);
        this.setLayout(new BorderLayout());        
        this.add(bp, BorderLayout.NORTH);
        this.add(ip);        
    
        bp.bounce.addMouseListener(
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
                    GameState.state = GameState.PREPARE;
                    startTime = System.currentTimeMillis();
                }

                @Override
                public void mouseReleased(java.awt.event.MouseEvent arg0) {
                    GameState.state = GameState.LAUNCHED;
                    ppMain.setSpringTimer(false, display);
                    ppMain.launchBall();

                }
            }

        );
    }

    public void resetPanel(){
        ip.force.setText("Force: 0");
        ip.scoreLabel.setText("Score: 0");
    }

    public void updateClock(){
        display = System.currentTimeMillis() - startTime;
        ip.force.setText("Force: "+ Long.toString(display));
    }

    public void updateTransitionTime(){
        String[] info = ip.currRoundLabel.getText().split(" ");
        int roundNumber = Integer.parseInt(info[2]);
        if (roundNumber ==3){
            GameState.state = GameState.END;
        }
        else{
            ip.updateRound();
            GameState.state = GameState.IDLE;
        }
    }

    public int getFinalScore(){
        String[] info = ip.totalScoreLabel.getText().split(" ");
        int totalScore = Integer.parseInt(info[2]);        
        return totalScore;
    } 
}
