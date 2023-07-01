import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    JButton openPlaylistRadioButton;
    JButton createPlaylistRadioButton;
    JButton browsePlaylistsRadioButton;
    JButton deletePlaylistRadioButton , playMusic;
    ImageIcon i ;
    Login() {
    	dispose();
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
        setTitle("Music Library Project");
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        
        JLabel text = new JLabel("Welcome to your Music Library");
        add(text);
        text.setFont(new Font("Sans-serif", Font.BOLD, 48));
        text.setForeground(Color.WHITE);
        text.setBounds(400, 70, 800, 60);
        text.setOpaque(false);



        openPlaylistRadioButton = new JButton("Open Existing Playlist");
        openPlaylistRadioButton.setBackground(Color.BLACK);
        openPlaylistRadioButton.setForeground(Color.WHITE);
        add(openPlaylistRadioButton);
        openPlaylistRadioButton.setBounds(480, 200, 500, 40);
        openPlaylistRadioButton.setFont(new Font("Sans-serif", Font.ITALIC, 24));
        openPlaylistRadioButton.setHorizontalAlignment(SwingConstants.LEFT);
        openPlaylistRadioButton.setMargin(new Insets(0, 110, 0, 0));
        openPlaylistRadioButton.addActionListener(this);

        createPlaylistRadioButton = new JButton("Create a playlist");
        createPlaylistRadioButton.setBackground(Color.BLACK);
        createPlaylistRadioButton.setForeground(Color.WHITE);
        add(createPlaylistRadioButton);
        createPlaylistRadioButton.setFont(new Font("Sans-serif", Font.ITALIC, 24));
        createPlaylistRadioButton.setBounds(480, 260, 500, 40);
        createPlaylistRadioButton.setHorizontalAlignment(SwingConstants.LEFT);
        createPlaylistRadioButton.setMargin(new Insets(0, 110, 0, 0));
        createPlaylistRadioButton.addActionListener(this);

        browsePlaylistsRadioButton = new JButton("Browse playlists");
        browsePlaylistsRadioButton.setBackground(Color.BLACK);
        browsePlaylistsRadioButton.setForeground(Color.WHITE);
        add(browsePlaylistsRadioButton);
        browsePlaylistsRadioButton.setHorizontalAlignment(SwingConstants.LEFT);
        browsePlaylistsRadioButton.setMargin(new Insets(0, 110, 0, 0));
        browsePlaylistsRadioButton.setFont(new Font("Sans-serif", Font.ITALIC, 24));
        browsePlaylistsRadioButton.setBounds(480, 320, 500, 40);
        browsePlaylistsRadioButton.addActionListener(this);

        deletePlaylistRadioButton = new JButton("Delete Existing Playlist");
        deletePlaylistRadioButton.setBackground(Color.BLACK);
        deletePlaylistRadioButton.setForeground(Color.WHITE);
        add(deletePlaylistRadioButton);
        deletePlaylistRadioButton.setHorizontalAlignment(SwingConstants.LEFT);
        deletePlaylistRadioButton.setMargin(new Insets(0, 110, 0, 0));
        deletePlaylistRadioButton.setFont(new Font("Sans-serif", Font.ITALIC, 24));
        deletePlaylistRadioButton.setBounds(480, 380, 500, 40);
        deletePlaylistRadioButton.addActionListener(this);
        
        playMusic = new JButton("Play Music");
        playMusic.setBackground(Color.BLACK);
        playMusic.setForeground(Color.WHITE);
        add(playMusic);
        playMusic.setHorizontalAlignment(SwingConstants.LEFT);
        playMusic.setMargin(new Insets(0, 110, 0, 0));
        playMusic.setFont(new Font("Sans-serif", Font.ITALIC, 24));
        playMusic.setBounds(480, 440, 500, 40);
        playMusic.addActionListener(this);
        
        openPlaylistRadioButton.addActionListener(this);
        createPlaylistRadioButton.addActionListener(this);
        browsePlaylistsRadioButton.addActionListener(this);
        deletePlaylistRadioButton.addActionListener(this);
        playMusic.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == openPlaylistRadioButton) {
            try {
				new OpenPlaylist();        	dispose();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        } else if (ae.getSource() == createPlaylistRadioButton) {
            new CreatePlaylist();        	dispose();
        } else if (ae.getSource() == browsePlaylistsRadioButton) {
            try {
                new BrowsePlaylist();dispose();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == deletePlaylistRadioButton) {      
            try {
				new Delete();
				dispose();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        } else if (ae.getSource() == playMusic) {    
            new Player();dispose();
        }
    }

    public static void main(String[] args) {
    new Login();
    }
}
