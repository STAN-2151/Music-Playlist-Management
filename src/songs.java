

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class songs extends JFrame implements ActionListener
{ /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

String name;

	JButton yes , viewButton , addButton,removeButton;
	static String justuser , justplaylist;
	String userId , playlistId;	
	
	songs(String userid, String playlistid , String a)
	{	this.userId = userid;
		this.playlistId = playlistid;
		justuser = userid;
		justplaylist = playlistid;
		String ab = "Playlist Name: "+a; 
	name=a;

				
	 setVisible(true);
     setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
     setTitle("Music Library Project");
     getContentPane().setBackground(Color.BLACK);
     setLayout(null);
				
		JLabel text = new JLabel("Browse your playlist");
		add(text);
		text.setFont(new Font("sans-serif",Font.BOLD,68));
		text.setForeground(Color.WHITE);
		text.setBounds(400, 30, 800, 80);

		 	yes = new JButton("Go to main menu");
		 	yes.setBackground(Color.BLACK);
		 	yes.setForeground(Color.WHITE);
	        add(yes);
	        yes.setBounds(480, 700, 500, 40);
	        yes.setFont(new Font("Sans-serif", Font.ITALIC, 24));
	        yes.addActionListener(this);
			
			
			
			JLabel pname = new JLabel(ab);
			pname.setFont(new Font("sans-serif",Font.ITALIC,28));
			pname.setForeground(Color.WHITE);
			add(pname);
			pname.setBounds(560,140,400,40);
			pname.setFont(new Font("sans-serif",Font.BOLD,28));

			
			addButton = new JButton("Add Songs");
			addButton.setBounds(500,210,400,40);
			add(addButton);
			addButton.setBackground(Color.BLACK);
			addButton.setForeground(Color.WHITE);
			addButton.addActionListener(this);
			
			
			
			removeButton = new JButton("Remove Songs");
			removeButton.setBounds(500,280,400,40);
			add(removeButton);
			removeButton.setBackground(Color.BLACK);
			removeButton.setForeground(Color.WHITE);
			removeButton.addActionListener(this);
			
			
			
			viewButton = new JButton("View Songs");
			viewButton.setBounds(500,350,400,40);
			add(viewButton);
			viewButton.setBackground(Color.BLACK);
			viewButton.setForeground(Color.WHITE);
			viewButton.addActionListener(this);
			
			
			
			System.out.println("name of playlist is "+name);
			
		
	}
	
	public void actionPerformed(ActionEvent ae) 
	{
		
		if(ae.getSource()==yes) {
			new Login(userId);
		}
		else if(ae.getSource()== addButton) {
			dispose();
			new AddSongs(userId, playlistId , name);
		}
		else if(ae.getSource()==removeButton) {
			dispose();
			new RemoveSongs(userId, playlistId , name);
			
			}
		else if(ae.getSource() == viewButton){
			dispose();
			new ViewSongs(userId , playlistId ,name);
		}
		}
	
		
}

	

	
	

