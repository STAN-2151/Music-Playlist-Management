import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ps extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private Clip clip;

    public ps() {
        setTitle("Music Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        JButton playButton = new JButton("Play");
        playButton.setActionCommand("PLAY");
        playButton.addActionListener(this);
        panel.add(playButton);

        JButton stopButton = new JButton("Stop");
        stopButton.setActionCommand("STOP");
        stopButton.addActionListener(this);
        panel.add(stopButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setActionCommand("RESET");
        resetButton.addActionListener(this);
        panel.add(resetButton);

        JButton quitButton = new JButton("Quit");
        quitButton.setActionCommand("QUIT");
        quitButton.addActionListener(this);
        panel.add(quitButton);

        add(panel);
    }

    public static void main(String[] args) {
        ps player = new ps();
        player.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "PLAY":
                play("lose_yourself.wav");
                break;
            case "STOP":
                stop();
                break;
            case "RESET":
                reset();
                break;
            case "QUIT":
                quit();
                break;
        }
    }

    private void play(String path) {
        try {
            File musicFile = new File(path);

            if (musicFile.exists()) {
                AudioInputStream songStream = AudioSystem.getAudioInputStream(musicFile);
                clip = AudioSystem.getClip();
                clip.open(songStream);
                clip.start();
            } else {
                JOptionPane.showMessageDialog(this, "Music file doesn't exist");
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File not found: " + ex.getMessage());
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    private void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    private void reset() {
        if (clip != null) {
            clip.stop();
            clip.setMicrosecondPosition(0);
        }
    }

    private void quit() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
        dispose();
    }
}
