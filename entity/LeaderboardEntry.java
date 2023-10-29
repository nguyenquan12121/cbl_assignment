package entity;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LeaderboardEntry{
    public Integer score;
    public String userName;
    public Date date;
    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd HH:mm:ss yyyy");

    public LeaderboardEntry(Integer score){
        this.score = score;
        this.date = new Date();
    }

    public LeaderboardEntry(Date date, String name,Integer score){
        this.score = score;
        this.date = date;
        this.userName= name;
    }

    public void updateUsername(String name){
        this.userName = name;
    }

    @Override
    public String toString(){
        return sdf.format(this.date) + "-" + this.userName + "-"+this.score;
    }
    
}