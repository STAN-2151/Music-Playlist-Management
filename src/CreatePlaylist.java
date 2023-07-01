import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class CreatePlaylist extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    
    private JButton submitButton , backButton;
    private JTextField playlistNameField;

    CreatePlaylist() {
    	setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
        setTitle("Music Library Project");
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);

        JLabel textLabel = new JLabel("Create a new playlist");
        add(textLabel);
        textLabel.setFont(new Font("sans-serif", Font.BOLD, 64));
        textLabel.setForeground(Color.WHITE);
        textLabel.setBounds(400, 40, 720, 70);

        JLabel enterNameLabel = new JLabel("Enter name for playlist:");
        add(enterNameLabel);
        enterNameLabel.setFont(new Font("sans-serif", Font.BOLD, 26));
        enterNameLabel.setForeground(Color.WHITE);
        enterNameLabel.setBounds(480, 160, 350, 30);

        playlistNameField = new JTextField();
        playlistNameField.setBounds(780, 170, 200, 20);
        add(playlistNameField);

        submitButton = new JButton("SUBMIT");
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);
        add(submitButton);
        submitButton.setFont(new Font("sans-serif", Font.BOLD, 18));
        submitButton.setBounds(480, 230, 500, 40);
        submitButton.addActionListener(this);
        
        backButton = new JButton("Go to main menu");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        add(backButton);
        backButton.setFont(new Font("sans-serif", Font.BOLD, 18));
        backButton.setBounds(480, 300, 500, 40);
        backButton.addActionListener(this);


 
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitButton) {
            String name = playlistNameField.getText().trim();
            
            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Invalid input");
            }  else { 	if (name.contains(" ")) {
        				name  = name.replace(" ","_");
            			} 
                Conn obj = new Conn();
                String query = "CREATE TABLE " + name.toLowerCase() + " (songs VARCHAR(20), link VARCHAR(40));";
                
                try {
                    obj.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Playlist created");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }  if (ae.getSource() == backButton) {
            new Login();
            dispose();
        }
    }

    public static void main(String args[]) {
        try {
            new CreatePlaylist();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
