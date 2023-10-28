import java.util.List;

import entity.LeaderboardEntry;



class Game implements Runnable{
    PlayPanel pp;
    MenuPanel mainButtonPanel;
    boolean run = true;
    GameContainer gameContainer;
    Thread animator;
    LeaderboardEntry entry;
    List<LeaderboardEntry> list;
    final int TICKS = 128, FPS = 144;
    String filePath = "leaderboard.txt";
    private Integer totalScore;
    Long startTime;
    boolean startedTransition;
    public Game() {
        pp = new PlayPanel();
        mainButtonPanel = new MenuPanel(pp);
        gameContainer = new GameContainer(pp, mainButtonPanel);
        gameContainer.createAndShowGUI();
        GameState.state = GameState.IDLE;
        startAnimation();
    }

    public void startAnimation() {
        animator = new Thread(this);
        animator.start();
    }

    public void render(){
        mainButtonPanel.repaint();
        pp.repaint();
    }

    public void setUpEndScreen(){
        totalScore = mainButtonPanel.getFinalScore();
        gameContainer.exitPanel();
        animator.interrupt();
        entry = new LeaderboardEntry(totalScore);
        EndGameContainer.initPanel(entry);
        EndGameContainer.createAndShowGUI();
        run = false;
    }

    public void update(Long currTime){
        switch(GameState.state){
            //Start of the game
            case IDLE:
                startedTransition = false;
                mainButtonPanel.resetPanel();
                pp.reset();
                break;
            //User holding down button
            case PREPARE:
                mainButtonPanel.updateClock();
                pp.compressSpring();
                break;
            //User lets go of the button
            case LAUNCHED:
                pp.updateBallLaunch();
                break;
            //I wanted to add some delay here
            case TRANSITION:
            System.out.println(startedTransition);
                if (!startedTransition){
                    startTime = System.nanoTime();
                    startedTransition = true;
                }
                mainButtonPanel.updateTransitionTime(startTime, currTime);
                break;
            case END:
                //launch EndScreen
                setUpEndScreen();
                break;
            default:
                break;
        }
    }


    @Override
    public void run(){
        long beforeTime, currTime;
        double timePerTick = 1000000000/TICKS;
        double timePerFrame = 1000000000/FPS;
        //delta tick rate to deal with lags
        double deltaT = 0, deltaF = 0;
        beforeTime = System.nanoTime();
        while (run) {
            currTime = System.nanoTime();
            deltaT += (currTime - beforeTime)/timePerTick;
            deltaF += (currTime - beforeTime)/timePerFrame;
            beforeTime = currTime;
            if (deltaT >=1){
                //deltaT might be 1.02 since there are inheriant lags and the game should only be update when delta is 1 so decrement by 1 and the 0.02 delay is accounted for in the next tick
                update(currTime);
                deltaT--;
                
            }
            if (deltaF >=1){
                render();
                deltaF--;
            }
        }
    }
}