import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entity.LeaderboardEntry;

class LeaderboardPanel extends JPanel{
    List<LeaderboardEntry> recordList = new ArrayList<>();
    JTable jTable;

    public LeaderboardPanel(JFrame frame){
        //rows and columns
        JLabel label = new JLabel("LEADERBOARD");
        JButton back = new JButton("BACK");
        // this.setLayout(new GridLayout(1, 1);
        this.setBorder(new EmptyBorder(100, 50, 100, 50));
        this.setBackground(Color.RED);
        this.add(label);
        this.add(back);
        back.addActionListener(e->{
            frame.dispose();
            StartingContainer.createAndShowGUI();
        });

    }
    //Game calls EndGameContainer which will then call this method to get the list
    public void addLeaderboard(List<LeaderboardEntry> list){
        this.recordList = list;
    }

    public void initTable(){
        jTable = getJTable();
        setUpTableData();
        jTable.setRowHeight(40);
        this.add(jTable);        
    }

    //init jtable
    private JTable getJTable() {
        if (jTable == null) {
            jTable = new JTable() {
                public boolean isCellEditable(int nRow, int nCol) {
                    return false;
                }
            };
        }
        String[] colName = { "Date", "Username", "Score"};
        DefaultTableModel contactTableModel = (DefaultTableModel) jTable.getModel();
        contactTableModel.setColumnIdentifiers(colName);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return jTable;
    }

    //populate table with data
    public void setUpTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        for (int i = 0; i <recordList.size(); i++) {
            String[] data = new String[3];
            data[0] = recordList.get(i).date.toString();
            data[1] = recordList.get(i).userName;
            data[2] = String.valueOf(recordList.get(i).score);
            tableModel.addRow(data);
        }
        jTable.setModel(tableModel);
    }

    public void refreshTable(){
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.setRowCount(0);
    }
}
