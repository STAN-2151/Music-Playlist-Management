import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.*;

public class registration extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField name_text , num_text , email_text , pwd_text , cnf_text , cap_text;
	JButton submit , old_user , clear;
char[] ar = new char[5];	
	registration(){
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
        text.setBounds(400, 10, 800, 60);
        
        JLabel reg_label = new JLabel("Registration Page");
        add(reg_label);
        reg_label.setFont(new Font("Sans-serif", Font.BOLD, 32));
        reg_label.setForeground(Color.WHITE);
        reg_label.setBounds(600, 80, 800, 60);
        
        JLabel name_label = new JLabel("Enter Name:");
        add(name_label);
        name_label.setFont(new Font("Sans-serif", Font.BOLD, 24));
        name_label.setForeground(Color.WHITE);
        name_label.setBounds(450, 140, 223, 60);
        name_label.setHorizontalAlignment(SwingConstants.RIGHT);
        
        name_text = new JTextField();
        name_text.setBounds(683, 158, 223, 27);
        add(name_text);

        
        JLabel number_label = new JLabel("Enter Number:");
        add(number_label);
        number_label.setFont(new Font("Sans-serif", Font.BOLD, 24));
        number_label.setForeground(Color.WHITE);
        number_label.setBounds(450, 200, 223, 60);
        number_label.setHorizontalAlignment(SwingConstants.RIGHT);
        
        num_text = new JTextField();
        num_text.setBounds(683, 221, 223, 27);
        add(num_text);

        
        JLabel email_label = new JLabel("Enter Email:");
        add(email_label);
        email_label.setFont(new Font("Sans-serif", Font.BOLD, 24));
        email_label.setForeground(Color.WHITE);
        email_label.setBounds(450, 260, 223, 60);
        email_label.setHorizontalAlignment(SwingConstants.RIGHT);
        
        email_text = new JTextField();
        email_text.setBounds(683, 281, 223, 27);
        add(email_text);

        
        JLabel pwd_label = new JLabel("Enter Password:");
        add(pwd_label);
        pwd_label.setFont(new Font("Sans-serif", Font.BOLD, 24));
        pwd_label.setForeground(Color.WHITE);
        pwd_label.setBounds(450, 320, 223, 60);
        pwd_label.setHorizontalAlignment(SwingConstants.RIGHT);
        
        pwd_text = new JTextField();
        pwd_text.setBounds(683, 341, 223, 27);
        add(pwd_text);

        
        JLabel cnf_label1 = new JLabel("Confirm Password:");
        add(cnf_label1);
        cnf_label1.setFont(new Font("Sans-serif", Font.BOLD, 24));
        cnf_label1.setForeground(Color.WHITE);
        cnf_label1.setBounds(450, 380, 223, 60);
        cnf_label1.setHorizontalAlignment(SwingConstants.RIGHT);
        
        cnf_text = new JTextField();
        cnf_text.setBounds(683, 401, 223, 27);
        add(cnf_text);
        
        JLabel captcha_label = new JLabel("Enter Captcha:");
        add(captcha_label);
        captcha_label.setFont(new Font("Sans-serif", Font.BOLD, 24));
        captcha_label.setForeground(Color.WHITE);
        captcha_label.setBounds(450, 440, 223, 60);
        captcha_label.setHorizontalAlignment(SwingConstants.RIGHT);
        
        Random random = new Random();
        int minValue = 48; // ASCII value for '0'
        int maxValue = 90; // ASCII value for 'Z'
        
        for (int i = 0; i < ar.length; i++) {
        	   int randomValue;
        do {
             randomValue = random.nextInt(maxValue - minValue + 1) + minValue;
        } while (randomValue > 57 && randomValue < 65); // Exclude symbols

        ar[i] = (char) randomValue;
        }
    
        String captcha = new String(ar);
        JLabel captcha1_label = new JLabel(captcha);
        add(captcha1_label);
        captcha1_label.setFont(new Font("Arial", Font.BOLD, 24));
        captcha1_label.setBackground(Color.WHITE);
        captcha1_label.setForeground(Color.RED);
        captcha1_label.setBounds(680, 443,120 , 60);
        
        cap_text = new JTextField();
        cap_text.setBounds(790, 460, 113, 27);
        add(cap_text);
        
        submit = new JButton("Submit");
		add(submit);
		submit.setBounds(480,520,200,30);
		submit.setBackground(Color.BLACK);
		submit.setForeground(Color.WHITE);
		submit.addActionListener(this);
		submit.setFont(new Font("Sans-serif", Font.BOLD, 16));

		
		clear = new JButton("Clear");
		add(clear);
		clear.setBounds(700,520,210,30);
		clear.setBackground(Color.BLACK);
		clear.setForeground(Color.WHITE);
		clear.addActionListener(this);
		clear.setFont(new Font("Sans-serif", Font.BOLD, 16));

		
		old_user = new JButton("Already Registered ?");
		add(old_user);
		old_user.setBounds(480,570,430,30);
		old_user.setBackground(Color.BLACK);
		old_user.setForeground(Color.WHITE);
		old_user.addActionListener(this);
		old_user.setFont(new Font("Sans-serif", Font.BOLD, 16));


	}

	public static void main(String[] args) {
		new registration();
	}

	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == clear) {
			new registration();
			dispose();
		}
		if( e.getSource() == old_user) {
			new Old_user();
			dispose();
		}
	}

}
