import java.util.Date;

class LeaderboardEntry{
    Integer score;
    String userName;
    Date date;
    public LeaderboardEntry(Integer score){
        this.score = score;
        this.date = new Date();
    }
    public void updateUsername(String name){
        this.userName = name;
    }
    
}