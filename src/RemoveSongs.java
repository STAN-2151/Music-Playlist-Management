
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class RemoveSongs extends JFrame implements ActionListener {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String name,songList;
String[] songs = new String[10];
String[] songId = new String[10];
JButton sub;
JTextField remSong;
JButton yes, viewButton, previous;
JRadioButton[] sList;
ButtonGroup musicRadio = new ButtonGroup();
String userId , playlistId ;


	public RemoveSongs(String userid, String playlistid , String a) {
		name = a;
		this.userId = userid;
		this.playlistId = playlistid;

		 setVisible(true);
	     setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
	     setTitle("Music Library Project");
	     getContentPane().setBackground(Color.BLACK);
	     setLayout(null);
				
		JLabel text = new JLabel("Remove Songs from '"+a.toUpperCase()+"'");
		add(text);
		text.setFont(new Font("sans-serif",Font.BOLD,54));
		text.setForeground(Color.WHITE);
        text.setBounds(350, 10, 1000, 80);
		
		
		

		
		JLabel choose = new JLabel("Chooose from below");
		add(choose);
		choose.setFont(new Font("sans-serif",Font.BOLD,22));
		choose.setForeground(Color.WHITE);
		choose.setBounds(400,160,440,20);
		
		
		sub = new JButton("Delete");
		sub.setBackground(Color.BLACK);
		sub.setForeground(Color.WHITE);
		sub.setFont(new Font("sans-serif",Font.BOLD,22));
		add(sub);
		sub.setBounds(450, 112, 500, 40);
		sub.addActionListener(this);
	
		 int i = 0;

	        try {
	            Conn obj = new Conn();
	            String query2 = "SELECT distinct s.song_name , sc.song_id FROM secret AS sc JOIN song AS s ON sc.song_id = s.song_id WHERE sc.user_id = '"+userId+ "' AND sc.playlist_id = '"+playlistId+"';";

	            ResultSet rs = obj.s.executeQuery(query2);
	            while (rs.next()) {
	                songs[i] = "" + rs.getString("song_name");
	                songId[i] =rs.getString("song_id");
	                i++;
	            }
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }

	     

	        sList = new JRadioButton[i]; // Update here

	        i = 0;
	        int bound = 200;
	        while (i < sList.length) {
	            sList[i] = new JRadioButton(songs[i]);
	            add(sList[i]);
	            sList[i].setBounds(400, bound, 150, 20);
	            sList[i].setBackground(Color.BLACK);
	            sList[i].setForeground(Color.WHITE);
	            sList[i].setBorder(null);
	            musicRadio.add(sList[i]);
	            bound += 30;
	            i++;
	        }
	
		
	    viewButton = new JButton("View Songs");
		viewButton.setBounds(450,600,500,40);
		add(viewButton);
		viewButton.setFont(new Font("Sans-serif", Font.ITALIC, 24));
		viewButton.setBackground(Color.BLACK);
		viewButton.setForeground(Color.WHITE);
		viewButton.addActionListener(this);
		
			previous = new JButton("Previous Menu");
			previous.setBounds(450,650,500,40);
			add(previous);
			previous.setFont(new Font("Sans-serif", Font.ITALIC, 24));
			previous.setBackground(Color.BLACK);
			previous.setForeground(Color.WHITE);
			previous.addActionListener(this);

		yes = new JButton("Go to Main Menu");
        yes.setBackground(Color.BLACK);
        yes.setForeground(Color.WHITE);
        add(yes);
        yes.setBounds(450, 700, 500, 40);
        yes.setFont(new Font("Sans-serif", Font.ITALIC, 24));
        yes.addActionListener(this);
	
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== yes) {
			new Login(userId);
			dispose();
		}
		 if(e.getSource()== viewButton) {
			new ViewSongs(userId , playlistId ,name);
			dispose();
		}
		 if(e.getSource()== previous) {
				new songs(userId, playlistId,name);
				dispose();
			}
		else if(e.getSource() == sub) {
			int count = 0;
			for (int i = 0; i < sList.length; i++) {
                if (sList[i].isSelected()) { count = 1;
                    Conn obj = new Conn();
                    String query = "delete from secret where song_id = '"+songId[i]+"' and user_id = '"+userId+"' and playlist_id = '"+playlistId+"' ;"; 
                    try {
						obj.s.executeUpdate(query);
					} catch (SQLException ae) {
						ae.printStackTrace();
					}
                    JOptionPane.showMessageDialog(null, "Song removed");
                    
                    new RemoveSongs(userId, playlistId,name);
                    	dispose(); return;
                }
                }
			if(count == 0)                    JOptionPane.showMessageDialog(null, "Select a song first");

			}
			}
	
		
	public static void main(String args[]) {
		new RemoveSongs("FDSJDBHASJ", "CAYDIHBRAG", "HONEY_SINGH");
	}
}
