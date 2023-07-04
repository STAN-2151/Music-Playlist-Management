import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.*;

public class OpenPlaylist extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JButton sub,backButton;
    private JTextField playlistNameField;
    String[] names;
	String[] p_id;
    JRadioButton[] pList;
    ButtonGroup musicRadio;
    String userId , playlistId;
    

    OpenPlaylist(String userid) throws SQLException {
    	this.userId = userid;
    	 setVisible(true);
         setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
         setTitle("Music Library Project");
         getContentPane().setBackground(Color.BLACK);
         setLayout(null);

        JLabel titleLabel = new JLabel("Open your playlist");
        add(titleLabel);
        titleLabel.setFont(new Font("sans-serif", Font.BOLD, 64));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(400, 30, 800, 80);

     
        
        backButton = new JButton("Go to main menu");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        add(backButton);
        backButton.setBounds(480, 700, 500, 40);
        backButton.setFont(new Font("Sans-serif", Font.ITALIC, 24));
        backButton.addActionListener(this);

        JLabel or = new JLabel("Top Playlists are:");
        add(or);
        or.setFont(new Font("sans-serif", Font.BOLD, 18));
        or.setForeground(Color.WHITE);
        or.setBounds(510, 130, 720, 60);
        
        
        Conn obj = new Conn();
        String query =  "Select * from playlist where user_id = '"+userId+"';";

        ResultSet resultSet = obj.s.executeQuery(query);
        names = new String[20];
        p_id = new String[20];
        int i = 0;
        while (resultSet.next()) {
            names[i] = resultSet.getString("playlist_name");
            p_id[i] = resultSet.getString("playlist_id");
            i++;
        }
        
        pList = new JRadioButton[i];
        musicRadio = new ButtonGroup(); // Initialize ButtonGroup

        int bound = 200;
        int j;
        for ( j = 0; j < i && j<10 ; j++) {
            pList[j] = new JRadioButton(names[j].toUpperCase());
            add(pList[j]);
            pList[j].setBounds(550, bound, 450, 20);
            pList[j].setBackground(Color.BLACK);
            pList[j].setForeground(Color.WHITE);
            pList[j].setBorder(null);
            pList[j].setFont(new Font("Sans-seriff", Font.BOLD,14));
            musicRadio.add(pList[j]);
            pList[j].addActionListener(this);
            bound += 40;
        }
        while( j > 10 && j<21) {
        	pList[j] = new JRadioButton(names[j].toUpperCase());
            add(pList[j]);
            pList[j].setBounds(700, bound, 450, 20);
            pList[j].setBackground(Color.BLACK);
            pList[j].setForeground(Color.WHITE);
            pList[j].setBorder(null);
            pList[j].setFont(new Font("Sans-seriff", Font.BOLD,14));
            musicRadio.add(pList[j]);
            pList[j].addActionListener(this);
            bound += 40;
        	
        }
        
        
    }

//    private boolean isPlaylistExist(String playlistName) {
//    	
//    	
//        try {
//            Conn obj = new Conn();
//            String query = "SHOW TABLES;";
//            ResultSet resultSet = obj.s.executeQuery(query);
//            String[] playlists = new String[20];
//            int i = 0;
//            while (resultSet.next()) {
//                playlists[i] = "" + resultSet.getString(1);
//                i++;
//            }
//            return Arrays.asList(playlists).contains(playlistName);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    public void actionPerformed(ActionEvent ae) {
    	for (int i = 0; i < pList.length; i++) {
            if (pList[i].isSelected()) { 
                dispose();
            	new songs(userId , p_id[i] , names[i] );
                return;
        
                }   
            
        }
    	
//        if (ae.getSource() == sub) {        	
//            String playlistName = playlistNameField.getText().trim();
//            if (playlistName.isEmpty()) {
//                JOptionPane.showMessageDialog(null, "Invalid input");
//            } else {
//            	if (playlistName.contains(" ")) {
//            		playlistName  = playlistName.replace(" ","_");
//        			} 
//            	
//                if (isPlaylistExist(playlistName)) {
//                	dispose();
//                    new songs(userId,playlistId,playlistName);
//                } else {
//                    JOptionPane.showMessageDialog(null, "Playlist doesn't exist");
//                }
//            }}
          if (ae.getSource()==backButton) {
            dispose();new Login(userId).setVisible(true);

        }
    }

    public static void main(String args[]) throws SQLException {
        new OpenPlaylist("FDSJDBHASJ");
    }
}
