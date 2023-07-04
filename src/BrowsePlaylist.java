import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class BrowsePlaylist extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    JRadioButton[] pList;
    ButtonGroup musicRadio;
    String names[] , playlist_id[];
    JButton yes;
    String userId;
    static String  just;
    public BrowsePlaylist(String userid) throws SQLException {
        this.userId = userid;
        just = userid;
    	
    	setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
        setTitle("Music Library Project");
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);

        JLabel text = new JLabel("All available Playlists are:");
        add(text);
        text.setFont(new Font("Sans-serif", Font.BOLD, 48));
        text.setForeground(Color.WHITE);
        text.setBounds(400, 40, 920, 60);

        Conn obj = new Conn();
        String query = "Select * from playlist where user_id = '"+userId+"';";
        ResultSet resultSet = obj.s.executeQuery(query);
        names = new String[20];
        playlist_id = new String[20];

        int i = 0;
        while (resultSet.next()) {
            names[i] = resultSet.getString("playlist_name");
            playlist_id[i] = resultSet.getString("playlist_id");
            i++;
        }

        pList = new JRadioButton[i];
        musicRadio = new ButtonGroup(); // Initialize ButtonGroup

        int bound = 160;
        for (int j = 0; j < i ; j++) {
            pList[j] = new JRadioButton(names[j].toUpperCase());
            add(pList[j]);
            pList[j].setBounds(500, bound, 350, 20);
            pList[j].setBackground(Color.BLACK);
            pList[j].setForeground(Color.WHITE);
            pList[j].setBorder(null);
            pList[j].setFont(new Font("Sans-seriff", Font.BOLD,16));
            musicRadio.add(pList[j]);
            pList[j].addActionListener(this);
            bound += 40;
        }



        yes = new JButton("Go to Main Menu");
        yes.setBackground(Color.BLACK);
        yes.setForeground(Color.WHITE);
        add(yes);
        yes.setBounds(480, 700, 500, 40);
        yes.setFont(new Font("Sans-serif", Font.ITALIC, 24));
        yes.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == yes) {
            dispose();
            new Login(userId);
            dispose();
        }
        
        for (int i = 0; i < pList.length; i++) {
            if (pList[i].isSelected()) {
            	new songs(userId , playlist_id[i] , names[i] );
            	dispose();
            	}
        }        
    }

    public static void main(String args[]) throws SQLException {
        try {
            new BrowsePlaylist(just);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
