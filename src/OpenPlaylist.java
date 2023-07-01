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
    JRadioButton[] pList;
    ButtonGroup musicRadio;

    OpenPlaylist() throws SQLException {
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

        JLabel nameLabel = new JLabel("Enter name of playlist:");
        add(nameLabel);
        nameLabel.setFont(new Font("sans-serif", Font.ITALIC, 22));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(480, 160, 350, 30);

        playlistNameField = new JTextField();
        playlistNameField.setBounds(720, 165, 160, 25);
        add(playlistNameField);

        sub = new JButton("SUBMIT");
        sub.setBackground(Color.BLACK);
        sub.setForeground(Color.WHITE);
        add(sub);
        sub.setBounds(905, 165, 150, 28);
        sub.addActionListener(this);
        
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
        or.setBounds(510, 230, 720, 60);
        
        
        Conn obj = new Conn();
        String query = "SELECT table_name\r\n"
        		+ "FROM information_schema.tables\r\n"
        		+ "WHERE table_schema = 'musiclibrary'\r\n"
        		+ "ORDER BY table_rows DESC\r\n"
        		+ "LIMIT 5;";
        ResultSet resultSet = obj.s.executeQuery(query);
        names = new String[20];

        int i = 0;
        while (resultSet.next()) {
            names[i] = resultSet.getString(1);
            i++;
        }
        
        pList = new JRadioButton[i];
        musicRadio = new ButtonGroup(); // Initialize ButtonGroup

        int bound = 300;
        for (int j = 0; j < i ; j++) {
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
        
        
    }

    private boolean isPlaylistExist(String playlistName) {
    	
    	
        try {
            Conn obj = new Conn();
            String query = "SHOW TABLES;";
            ResultSet resultSet = obj.s.executeQuery(query);
            String[] playlists = new String[20];
            int i = 0;
            while (resultSet.next()) {
                playlists[i] = "" + resultSet.getString(1);
                i++;
            }
            return Arrays.asList(playlists).contains(playlistName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void actionPerformed(ActionEvent ae) {
    	for (int i = 0; i < pList.length; i++) {
            if (pList[i].isSelected()) { 
                dispose();
                new songs(names[i]);
                return;
        
                }   
            
        }
    	
        if (ae.getSource() == sub) {        	
            String playlistName = playlistNameField.getText().trim();
            if (playlistName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid input");
            } else {
            	if (playlistName.contains(" ")) {
            		playlistName  = playlistName.replace(" ","_");
        			} 
            	
                if (isPlaylistExist(playlistName)) {
                	dispose();
                    new songs(playlistName);
                } else {
                    JOptionPane.showMessageDialog(null, "Playlist doesn't exist");
                }
            }
        }  if (ae.getSource()==backButton) {
            dispose();new Login().setVisible(true);

        }
    }

    public static void main(String args[]) throws SQLException {
        new OpenPlaylist();
    }
}
