import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class Delete extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JButton sub , yes;
    private JTextField DltPly;
    String[] names , playlist_id;
    JRadioButton[] pList;
    ButtonGroup musicRadio;
    String userId;
    static String  just;
    public Delete(String userid) throws SQLException {
    	this.userId = userid;
    just = userId;
    	
    	 setVisible(true);
         setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
         setTitle("Music Library Project");
         getContentPane().setBackground(Color.BLACK);
         setLayout(null);

        JLabel text = new JLabel("Delete Existing Playlist");
        add(text);
        text.setFont(new Font("sans-serif", Font.BOLD, 64));
        text.setForeground(Color.WHITE);
        text.setBounds(400, 30, 800, 80);

        JLabel entName = new JLabel("Enter name of playlist : ");
        add(entName);
        entName.setFont(new Font("sans-serif", Font.ITALIC, 22));
        entName.setForeground(Color.WHITE);
        entName.setBounds(480, 160, 350, 30);

        DltPly = new JTextField();
        DltPly.setBounds(720, 165, 160, 25);
        add(DltPly);

        sub = new JButton("SUBMIT");
        sub.setBackground(Color.BLACK);
        sub.setForeground(Color.WHITE);
        add(sub);
        sub.setBounds(905, 165, 150, 28);
        sub.addActionListener(this);

        yes = new JButton("Go to Main Menu");
        yes.setBackground(Color.BLACK);
        yes.setForeground(Color.WHITE);
        add(yes);
        yes.setBounds(480, 700, 500, 40);
        yes.setFont(new Font("Sans-serif", Font.ITALIC, 24));
        yes.addActionListener(this);

 
        
        JLabel or = new JLabel("Top Playlists are:");
        add(or);
        or.setFont(new Font("sans-serif", Font.BOLD, 18));
        or.setForeground(Color.WHITE);
        or.setBounds(510, 230, 720, 60);
        
        
        Conn obj = new Conn();
        String query = "Select playlist_name , playlist_id from playlist where user_id = '"+userId+"' limit 5;";
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

        int bound = 300;
        for (int j = 0; j < i ; j++) {
            pList[j] = new JRadioButton(names[j].toUpperCase());
            add(pList[j]);
            pList[j].setBounds(560, bound, 450, 20);
            pList[j].setBackground(Color.BLACK);
            pList[j].setForeground(Color.WHITE);
            pList[j].setBorder(null);
            pList[j].setFont(new Font("Sans-seriff", Font.BOLD,14));
            musicRadio.add(pList[j]);
            pList[j].addActionListener(this);
            bound += 40;
        }
        
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == sub) {
            for (int i = 0; i < pList.length; i++) {
                if (pList[i].isSelected()) { 
                    Conn obj = new Conn();
                    String query = "delete from playlist where playlist_id = '"+playlist_id[i]+"';";
                    try {
						obj.s.executeUpdate(query);
					} catch (SQLException e) {
						e.printStackTrace();
					}
                    JOptionPane.showMessageDialog(null, "Playlist removed");
                    dispose();
                    new Login(userId);
                    return;
            }}
            
        	
            try {
                String name = DltPly.getText().trim();

                if (name.isEmpty() ) {
                    JOptionPane.showMessageDialog(null, "Invalid input");
                } else {
                	if (name.contains(" ")) {
        				name  = name.replace(" ","_");
            			} 
                    Conn obj = new Conn();
                    String query = "delete from playlist where playlist_name = '"+name+"';";
                    obj.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Playlist removed");
                    dispose(); // Close the frame after deleting the playlist
                    new Login(userId);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occurred while deleting the playlist");
            }
        	
            }	
        	

          if (ae.getSource() == yes) {
           dispose(); new Login(userId);
            dispose();

        }
        

    }


    public static void main(String args[]) {
        try {
            new Delete(just);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
