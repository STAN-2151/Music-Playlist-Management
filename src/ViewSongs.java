import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.ResultSet;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class ViewSongs extends JFrame implements ActionListener {
    JRadioButton add, remove;
    JButton MMenu ,PMenu , previous;
    String songList, name;
    String songs[] = new String[20];
    String links[] = new String[20];
    ButtonGroup musicRadio = new ButtonGroup();
    JButton sub, play1, pause1, resume;
    static Clip clip;
    static long pos = 0;
    JRadioButton[] sList;

    private static final long serialVersionUID = 1L;

    public ViewSongs(String name) {
        this.name = name;
        
         setVisible(true);
	     setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
	     setTitle("Music Library Project");
	     getContentPane().setBackground(Color.BLACK);
	     setLayout(null);
	     
        int i = 0;

        try {
            Conn obj = new Conn();
            String query2 = "select distinct * from " + name;
            ResultSet rs = obj.s.executeQuery(query2);
            while (rs.next()) {
                songs[i] = "" + rs.getString("songs");
                links[i] = "" + rs.getString("link");
                i++;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        JLabel entName = new JLabel("Available Songs in Playlist '" + name.toUpperCase() + "'");
        add(entName);
        entName.setFont(new Font("sans-serif", Font.BOLD, 46));
        entName.setForeground(Color.WHITE);
        entName.setBounds(350, 20, 800, 60);

        sList = new JRadioButton[i]; // Update here

        i = 0;
        int bound = 120;
        while (i < sList.length) {
            sList[i] = new JRadioButton(songs[i]);
            add(sList[i]);
            sList[i].setBounds(400, bound, 150, 20);
            sList[i].setBackground(Color.BLACK);
            sList[i].setForeground(Color.WHITE);
            sList[i].setBorder(null);
            musicRadio.add(sList[i]);
            bound += 35;
            i++;
        }

        play1 = new JButton("Play / Reset");
        add(play1);
        play1.setBounds(780, 120, 180, 30);
        play1.setBackground(Color.BLACK);
        play1.setFont(new Font("sans-serif",Font.BOLD,16));
        play1.setForeground(Color.WHITE);
        play1.addActionListener(this);

        pause1 = new JButton("Pause");
        add(pause1);
        pause1.setBounds(780, 170, 180, 30);
        pause1.setFont(new Font("sans-serif",Font.BOLD,16));
        pause1.setBackground(Color.BLACK);
        pause1.setForeground(Color.WHITE);
        pause1.addActionListener(this);

        resume = new JButton("Resume");
        add(resume);
        resume.setBounds(780, 220, 180, 30);
        resume.setFont(new Font("sans-serif",Font.BOLD,16));
        resume.setBackground(Color.BLACK);
        resume.setForeground(Color.WHITE);
        resume.addActionListener(this);


        PMenu = new JButton("Add More Songs ");
        PMenu.setBounds(500, 600, 380, 35);
        add(PMenu);
        PMenu.setFont(new Font("sans-serif",Font.ITALIC,24));
        PMenu.setBackground(Color.BLACK);
        PMenu.setForeground(Color.WHITE);
        PMenu.addActionListener(this);

        previous = new JButton("Previous Menu");
        previous.setBounds(500, 650, 380, 35);
        add(previous);
        previous.setFont(new Font("sans-serif",Font.ITALIC,24));
        previous.setBackground(Color.BLACK);
        previous.setForeground(Color.WHITE);
        previous.addActionListener(this);

        MMenu = new JButton("Main Menu");
        MMenu.setBounds(500, 700, 380, 35);
        add(MMenu);
        MMenu.setFont(new Font("sans-serif",Font.ITALIC,24));
        MMenu.setBackground(Color.BLACK);
        MMenu.setForeground(Color.WHITE);
        MMenu.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==MMenu) {
            dispose();
            new Login();
        }
        if (e.getSource()==PMenu) {
            dispose();
            new AddSongs(name);
        }
        if (e.getSource()==previous) {
            dispose();
            new songs(name);
        }

        for (int i = 0; i < sList.length; i++) {
            if (sList[i].isSelected()) {
                if (e.getSource() == play1) {
                    try {
                        playSong(links[i]);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                if (e.getSource() == pause1) {
                    pause();
                }
                if (e.getSource() == resume) {
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
                Clip newClip = AudioSystem.getClip();
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

    public static void main(String args[]) {
        new ViewSongs("demo");
    }
}
