package ui;

import entity.LeaderboardEntry;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



class LeaderboardPanel extends JPanel {
    private BufferedImage backgroundImage;
    List<LeaderboardEntry> recordList = new ArrayList<>();
    JTable jTable;
    /** Constructor.
    * 
    */

    public LeaderboardPanel(JFrame frame) {
        String backgroundPath = "images/menu_background.jpg";
        try {
            File backFile = new File(backgroundPath);
            backgroundImage = ImageIO.read(backFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //rows and columns
        this.setLayout(new BorderLayout());
        JLabel label = new JLabel("LEADERBOARD");
        label.setFont(new Font("Arial", Font.BOLD, 26));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.GREEN);        
        JButton back = new JButton();
        back.setIcon(new ImageIcon("images/Exit_Button.png")); 
        back.setBorder(null);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);        
        this.setBorder(new EmptyBorder(100, 50, 100, 50));
        this.setBackground(Color.RED);
        this.add(label, BorderLayout.NORTH);
        this.add(back, BorderLayout.SOUTH);
        back.addActionListener(e -> {
            frame.dispose();
            StartingContainer.createAndShowGUI();
        });
        this.setPreferredSize(new Dimension(1000, 720));

    }

    /** Game calls EndGameContainer which will then call this method 
     * to get the list.
    * 
    */    
    public void addLeaderboard(List<LeaderboardEntry> list) {
        this.recordList = list;
    }

    /** Init table.
    * 
    */
    public void initTable() {
        jTable = getJTable();
        setUpTableData();
        jTable.setRowHeight(40);
        this.add(jTable, BorderLayout.CENTER);        
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

    /** Populate table with data.
    * 
    */
    public void setUpTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        for (int i = 0; i < recordList.size(); i++) {
            String[] data = new String[3];
            data[0] = recordList.get(i).date.toString();
            data[1] = recordList.get(i).userName;
            data[2] = String.valueOf(recordList.get(i).score);
            tableModel.addRow(data);
        }
        jTable.setModel(tableModel);
    }

    /** Refresh table.
    * 
    */
    public void refreshTable() {
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.setRowCount(0);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawBackground(g2d);
    }

    public void drawBackground(Graphics2D g2d) {
        g2d.drawImage(backgroundImage, 0, 0, 1200, 1200, null);
    }
}

