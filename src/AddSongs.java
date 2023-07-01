import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class AddSongs extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
String name;
String[] names = {"Lose Yourself", "Sidhu 295" , "Kabza" , "Stan" , "Velly" ,"Still Dre", "Till I Collapse", "Vaar" ,"The Last Ride", "Gangstas Paradise"};
String[] links = {"lose_yourself.wav","295.wav","Kabza.wav","Stan.wav","Velly.wav","Still Dre.wav","Till I Collapse.wav","Vaar.wav","The Last Ride.wav","Gangstas Paradise.wav" };
JRadioButton[] sList = new JRadioButton[links.length];
ButtonGroup musicRadio = new ButtonGroup();
JButton sub , play1, pause1 ,resume , view , main , previous;
JTextField addSong;
String songs[] = new String[40];
String songList;
static Clip clip;
static long pos=0;
	public AddSongs(String a) {
		name = a;
		String ab = "Playlist Name: "+a; 

		setVisible(true);
	     setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
	     setTitle("Music Library Project");
	     getContentPane().setBackground(Color.BLACK);
	     setLayout(null);
				
	     	JLabel text = new JLabel("Browse your playlist");
			add(text);
			text.setFont(new Font("sans-serif",Font.BOLD,68));
			text.setForeground(Color.WHITE);
			text.setBounds(380, 30, 800, 80);

		

		
		JLabel chooseSong = new JLabel ("Choose songs to add in Playlist  '"+a.toUpperCase()+"'" ) ;
		add(chooseSong);
		chooseSong.setFont(new Font("sans-serif",Font.BOLD,28));
		chooseSong.setForeground(Color.white);
		chooseSong.setBounds(450,120,700,60);
		
		int i =0;				int bound = 200;
		while( i < links.length/2) {
			sList[i] = new JRadioButton(names[i]);
			add(sList[i]);
			sList[i].setBounds(450, bound , 150 , 20);
			sList[i].setBackground(Color.black);
			sList[i].setForeground(Color.white);
			sList[i].setBorder(null);
			musicRadio.add(sList[i]);
			bound += 40;
			i++;
		} bound = 200;
		while( i < links.length) {
			sList[i] = new JRadioButton(names[i]);
			add(sList[i]);
			sList[i].setBounds(650, bound , 150 , 20);
			sList[i].setBackground(Color.black);
			sList[i].setForeground(Color.white);
			sList[i].setBorder(null);
			musicRadio.add(sList[i]);
			bound +=40;
			i++;
		} 
	
		
		play1 = new JButton("Play / Reset");
		add(play1);
		play1.setBounds(420,440,160,20);
		play1.setBackground(Color.BLACK);
		play1.setForeground(Color.WHITE);
		play1.addActionListener(this);
		
		pause1 = new JButton("Pause");
		add(pause1);
		pause1.setBounds(600,440,150,20);
		pause1.setBackground(Color.BLACK);
		pause1.setForeground(Color.WHITE);
		pause1.addActionListener(this);
		
		resume = new JButton("Resume");
		add(resume);
		resume.setBounds(770,440,160,20);
		resume.setBackground(Color.BLACK);
		resume.setForeground(Color.WHITE);
		resume.addActionListener(this);
		
		sub = new JButton("SUBMIT");
		sub.setBackground(Color.BLACK);
		sub.setForeground(Color.WHITE);
		add(sub);
		sub.setBounds(420,480,510,30);
		sub.addActionListener(this);
		
		
		view = new JButton("View Songs");
		add(view);
		view.setBounds(420,530,160,20);
		view.setBackground(Color.BLACK);
		view.setForeground(Color.WHITE);
		view.addActionListener(this);
		
		main = new JButton("Main Menu");
		add(main);
		main.setBounds(600,530,150,20);
		main.setBackground(Color.BLACK);
		main.setForeground(Color.WHITE);
		main.addActionListener(this);
		
		previous = new JButton("Previous Menu");
		add(previous);
		previous.setBounds(770,530,150,20);
		previous.setBackground(Color.BLACK);
		previous.setForeground(Color.WHITE);
		previous.addActionListener(this);
	
	}
	public void actionPerformed(ActionEvent e) {
		//this will show all added songs in current playlist
		if(e.getSource()==view) {
	    	new ViewSongs(name);
	    	dispose();
	    }
		if(e.getSource()==main) {
			dispose();
			new Login();
		}
		
		if(e.getSource()==previous) {
			dispose();
			new songs(name);
		}
		
		//code below is to add a particular song using checkbox
		if(e.getSource()==sub) {
			int count=0;
		for ( int i = 0 ; i < links.length ; i++) {
			if(sList[i].isSelected()) {	
				try {
					Conn obj = new Conn();	
					String query = "insert into "+name+" values('"+names[i]+"','"+links[i]+"');" ;
						obj.s.execute(query);
					 JOptionPane.showMessageDialog(null,names[i]+ " added!");
						count++;
					} catch (Exception e1) {
						e1.printStackTrace();
					}	
			}
			
		}
		if (count > 0) {
	        try {
	            // Execute ANALYZE TABLE query so it will show top playlists when deleting
	            Conn obj = new Conn();
	            String analyzeQuery = "ANALYZE TABLE " + name;
	            obj.s.execute(analyzeQuery);
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }
	    } 
			
			 if(count==0) {
				 JOptionPane.showMessageDialog(null, "Please choose a song to add");
			 }
		}
		
		//now we will do the coding of playing songs 
		
		for ( int i = 0 ; i < links.length ; i++) {
			if(sList[i].isSelected()) {
				if(e.getSource()==play1) {
					try {
								
						playSong(links[i]);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				if(e.getSource()==pause1) {
					pause();
				}
				if(e.getSource()==resume) {
					resume();
				}
			}

		}
	}

		
	public static void playSong(String path) throws Exception {
		    try {
		        File musicFile = new File(path);

		        if (musicFile.exists()) {
		            AudioInputStream songStream = AudioSystem.getAudioInputStream(musicFile);
		            Clip newClip = AudioSystem.getClip() ;
		            newClip.open(songStream);

		            if (clip != null && clip.isRunning()) {
		                clip.stop(); // Stop the previous clip
		            }

		            clip = newClip;
		            clip.start();
		        } else {
		            JOptionPane.showMessageDialog(null, "Music file doesn't exist");
		        }
		    } catch (Exception ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(null, "File not found: " + ex.getMessage());
		    }
		}
	public static void pause() {
		if (clip != null && clip.isRunning()) {
			pos = clip.getMicrosecondPosition();
            clip.stop();
        }
	}
	
		public static void resume() {
		    if (clip != null) {
		        clip.setMicrosecondPosition(pos);
		        clip.start();
		    } else {
		        JOptionPane.showMessageDialog(null, "No song is currently paused.");
		    }
		}	
	
//	public static void main (String args[]) {
//		new AddSongs("demo");
//	}
	
}
