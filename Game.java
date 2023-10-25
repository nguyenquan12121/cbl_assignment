import java.util.Random;

class Game implements Runnable{
    private Integer id;
    Random random;
    GameContainer gameContainer;
    final int ROUND_COUNT = 3;
    private int currRound = 1;
    private Integer currScore, totalScore;
    private Player player;
    public Game() {
        random = new Random();
        id = random.nextInt(100000, 999999);
        gameContainer = new GameContainer();
        gameContainer.createAndShowGUI();
    }
    public void updateRound(){
        currRound++;
    }
    public int getRound(){
        return currRound;
    }
    public Player getPlayer(String userName){
        player = new Player(currScore, userName);
        return player;
    }
    public Integer getID(){
        return id;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}