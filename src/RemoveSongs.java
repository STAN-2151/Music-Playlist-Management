
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
String[] songs = new String[40];
JButton sub;
JTextField remSong;
JButton yes, viewButton, previous;
JRadioButton[] sList;
ButtonGroup musicRadio = new ButtonGroup();

	public RemoveSongs(String a) {
		name = a;

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
		
		
		
		JLabel entName = new JLabel("Enter Song Name:");
		add(entName);
		entName.setFont(new Font("sans-serif",Font.ITALIC,22));
		entName.setForeground(Color.WHITE);
		entName.setBounds(400,100,440,40);
		
		JLabel choose = new JLabel("Or chooose from below");
		add(choose);
		choose.setFont(new Font("sans-serif",Font.ITALIC,18));
		choose.setForeground(Color.WHITE);
		choose.setBounds(400,160,440,20);
		
		remSong = new JTextField();
		remSong.setBounds(600, 114, 200, 20);
		add(remSong);
		
		sub = new JButton("Delete");
		sub.setBackground(Color.BLACK);
		sub.setForeground(Color.WHITE);
		sub.setFont(new Font("sans-serif",Font.BOLD,22));
		add(sub);
		sub.setBounds(810,112,300,26);
		sub.addActionListener(this);
	
		 int i = 0;

	        try {
	            Conn obj = new Conn();
	            String query2 = "select distinct * from " + name;
	            ResultSet rs = obj.s.executeQuery(query2);
	            while (rs.next()) {
	                songs[i] = "" + rs.getString("songs");
	                i++;
	            }
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }

	     

	        sList = new JRadioButton[i]; // Update here

	        i = 0;
	        int bound = 190;
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
			new Login();
			dispose();
		}
		 if(e.getSource()== viewButton) {
			new ViewSongs(name);
			dispose();
		}
		 if(e.getSource()== previous) {
				new songs(name);
				dispose();
			}
		else if(e.getSource() == sub) {
			for (int i = 0; i < sList.length; i++) {
                if (sList[i].isSelected()) { 
                    Conn obj = new Conn();
                    String query = "delete from "+name+" where songs='"+songs[i]+"';";
                    try {
						obj.s.executeUpdate(query);
					} catch (SQLException ae) {
						ae.printStackTrace();
					}
                    JOptionPane.showMessageDialog(null, "Song removed");
                    try {
        	            // Execute ANALYZE TABLE query so it will show top playlists when deleting
        	            Conn obj1 = new Conn();
        	            String analyzeQuery = "ANALYZE TABLE " + name;
        	            obj1.s.execute(analyzeQuery);
        	        } catch (Exception e1) {
        	            e1.printStackTrace();
        	        }
                    new RemoveSongs(name);
                    	dispose(); return;
                    
            }}
			
				String rem = (String) remSong.getText();
				if( rem.equals("") ) {
					JOptionPane.showMessageDialog(null,"Invalid input");
				}
				if (rem.contains(" ")) {
					JOptionPane.showMessageDialog(null,"You cannot add space in song name");
				}
				else {
					
				try {
				Conn obj = new Conn();	
				String query = "delete from "+name+" where songs='"+rem+"';";
					obj.s.executeUpdate(query);
					JOptionPane.showMessageDialog(null,"Song removed");
					try {
			            // Execute ANALYZE TABLE query so it will show top playlists when deleting
			            Conn obj1 = new Conn();
			            String analyzeQuery = "ANALYZE TABLE " + name;
			            obj1.s.execute(analyzeQuery);
			        } catch (Exception e1) {
			            e1.printStackTrace();
			        }
					new RemoveSongs(name);
                	dispose(); return;
                	} 
				catch (Exception e1) {
					e1.printStackTrace();
				}			
				}
			}
	
		}
	public static void main(String args[]) {
		new RemoveSongs("demo");
	}
}
