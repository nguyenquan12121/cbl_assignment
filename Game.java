import java.util.Random;

class Game{
    private Integer id;
    Random random;
    final int ROUND_COUNT = 3;
    private int currRound = 1;
    private Integer currScore;
    private Player player;
    public Game(){
        random = new Random();
        id = random.nextInt(100000, 999999);
        GameContainer.createAndShowGUI();
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
}