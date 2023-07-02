import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class Player extends JFrame implements ActionListener {
	
	String[] names = {"Lose Yourself", "Sidhu 295" , "Kabza" , "Stan" , "Velly" ,"Still Dre", "Till I Collapse", "Vaar" ,"The Last Ride", "Gangsta's Paradise"};
	String[] links = {"lose_yourself.wav","295.wav","Kabza.wav","Stan.wav","Velly.wav","Still Dre.wav","Till I Collapse.wav","Vaar.wav","The Last Ride.wav","Gangstas Paradise.wav" };
	JRadioButton[] sList = new JRadioButton[links.length];
	ButtonGroup musicRadio = new ButtonGroup();
	JButton play1, pause1 ,resume, next , previous , yes ;
	String songs[] = new String[40];
	String songList;
	static Clip clip = null;
	static long pos=0; int index = -1;
		public Player() {
		      
				setVisible(true);
		        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
		        setTitle("Music Library Project");
		        getContentPane().setBackground(Color.BLACK);
		        setLayout(null);
				
		        
			JLabel text = new JLabel("Select Song to Play");
			add(text);
			text.setFont(new Font("sans-serif",Font.BOLD,64));
			text.setForeground(Color.WHITE);
			text.setBounds(420,25,720,80);
			
			int i =0;				int bound = 150;
			while( i < links.length/2) {
				sList[i] = new JRadioButton(names[i]);
				add(sList[i]);
				sList[i].setBounds(500, bound , 150 , 20);
				sList[i].setBackground(Color.black);
				sList[i].setForeground(Color.white);
				sList[i].setBorder(null);
				musicRadio.add(sList[i]);
				bound += 40;
				i++;
			} bound = 150;
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
			play1.setBounds(480,360,130,20);
			play1.setBackground(Color.BLACK);
			play1.setForeground(Color.WHITE);
			play1.addActionListener(this);
			
			pause1 = new JButton("Pause");
			add(pause1);
			pause1.setBounds(630,360,120,20);
			pause1.setBackground(Color.BLACK);
			pause1.setForeground(Color.WHITE);
			pause1.addActionListener(this);
			
			resume = new JButton("Resume");
			add(resume);
			resume.setBounds(780,360,120,20);
			resume.setBackground(Color.BLACK);
			resume.setForeground(Color.WHITE);
			resume.addActionListener(this);
			
			next = new JButton("Next Song");
			add(next);
			next.setBounds(480,400,200,20);
			next.setBackground(Color.BLACK);
			next.setForeground(Color.WHITE);
			next.addActionListener(this);
			
			previous = new JButton("Previous Song");
			add(previous);
			previous.setBounds(700,400,200,20);
			previous.setBackground(Color.BLACK);
			previous.setForeground(Color.WHITE);
			previous.addActionListener(this);
		

			 	yes = new JButton("Go to Main Menu");
		        yes.setBackground(Color.BLACK);
		        yes.setForeground(Color.WHITE);
		        add(yes);
		        yes.setBounds(425, 700, 560, 40);
		        yes.setFont(new Font("Sans-serif", Font.ITALIC, 24));
		        yes.addActionListener(this);

		
		}
	
	
	
	
	
	
	public void actionPerformed(ActionEvent e) {
			if(e.getSource()==yes) {
				new Login();
				dispose();
			}
			
			for ( int i = 0 ; i < links.length ; i++) {
				if(sList[i].isSelected()) {
					if(e.getSource()==play1) {
						try {
									
							playSong(links[i]);
							index  = i;
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

		
		if (e.getSource()==next) {
		    if (clip == null) {
		        JOptionPane.showMessageDialog(null, "Please choose a song first");
		    } else {
		    	try {
		    		if(index<links.length-1) {
						playSong(links[index+1]);
						index++;
					 
		    	}
		    		else { playSong(links[0]);}
		    	}
		    	catch(Exception we) {
		    		we.printStackTrace();
		    	}
		    	
		    }
		}
		
		if (e.getSource()==previous) {
		    if (clip == null) {
		        JOptionPane.showMessageDialog(null, "Please choose a song first");
		    } else {
		    	try {
		    		if(index>-1) {
						playSong(links[index-1]);
						index--;
					 
		    	}
		    		else { playSong(links[links.length-1]);}
		    	}
		    	catch(Exception we) {
		    		we.printStackTrace();
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
	
	
	public static void main(String[] args) {
		new Player();
	}
	
}
