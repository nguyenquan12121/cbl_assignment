import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

class EndGameLeaderboardPanel extends JPanel{
    List<LeaderboardEntry> recordList;
    String[][] data = {
        { "kennys", "4031", "CSE" },
        { "Anand Jha", "6014", "IT" }        
    };
    String[] columnName = {"Date", "Username", "Score"};
    JTable recordTable;
    public EndGameLeaderboardPanel(){
        //rows and columns
        recordTable = new JTable(data,columnName);
        recordTable.setRowHeight(40);
        this.add(recordTable);
        this.setLayout(new GridLayout(1, 1));
        this.setBorder(new EmptyBorder(100, 50, 100, 50));
        this.setBackground(Color.RED);

    }
    public void addLeaderboard(List<LeaderboardEntry> list){
        this.recordList = list;
    }

    public String[][] processList(List<LeaderboardEntry> list){
        String[][] lol = {{"ha"}};
        return lol;
    }

}