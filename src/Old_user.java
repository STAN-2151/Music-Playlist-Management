import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;


public class Old_user extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField  num_text  , cap_text;
	JPasswordField pwd_text;
	JButton submit , new_user , clear;
	String captcha;
	String userId;
Old_user(){
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
        
        JLabel reg_label = new JLabel("Login Page");
        add(reg_label);
        reg_label.setFont(new Font("Sans-serif", Font.BOLD, 38));
        reg_label.setForeground(Color.WHITE);
        reg_label.setBounds(610, 80, 800, 60);
        
        JLabel number_label = new JLabel("Number Or Email:");
        add(number_label);
        number_label.setFont(new Font("Sans-serif", Font.BOLD, 24));
        number_label.setForeground(Color.WHITE);
        number_label.setBounds(450, 170, 223, 60);
        number_label.setHorizontalAlignment(SwingConstants.RIGHT);
        
        num_text = new JTextField();
        num_text.setBounds(683, 191, 223, 27);
        add(num_text);

        JLabel pwd_label = new JLabel("Enter Password:");
        add(pwd_label);
        pwd_label.setFont(new Font("Sans-serif", Font.BOLD, 24));
        pwd_label.setForeground(Color.WHITE);
        pwd_label.setBounds(450, 220, 223, 60);
        pwd_label.setHorizontalAlignment(SwingConstants.RIGHT);
        
        pwd_text = new JPasswordField();
        pwd_text.setBounds(683, 239, 223, 27);
        add(pwd_text);
        
        JLabel captcha_label = new JLabel("Enter   Captcha :");
        add(captcha_label);
        captcha_label.setFont(new Font("Sans-serif", Font.BOLD, 24));
        captcha_label.setForeground(Color.WHITE);
        captcha_label.setBounds(450, 265, 223, 60);
        captcha_label.setHorizontalAlignment(SwingConstants.RIGHT);
    
         captcha = helper.unique_captcha();
        JLabel captcha1_label = new JLabel(captcha);
        add(captcha1_label);
        captcha1_label.setFont(new Font("Arial", Font.BOLD, 24));
        captcha1_label.setBackground(Color.WHITE);
        captcha1_label.setForeground(Color.RED);
        captcha1_label.setBounds(680, 268,120 , 60);
        
        cap_text = new JTextField();
        cap_text.setBounds(790, 285, 118, 27);
        add(cap_text);
        
        submit = new JButton("Submit");
		add(submit);
		submit.setBounds(480,340,200,30);
		submit.setBackground(Color.BLACK);
		submit.setForeground(Color.WHITE);
		submit.addActionListener(this);
		submit.setFont(new Font("Sans-serif", Font.BOLD, 16));

		
		clear = new JButton("Clear");
		add(clear);
		clear.setBounds(700,340,210,30);
		clear.setBackground(Color.BLACK);
		clear.setForeground(Color.WHITE);
		clear.addActionListener(this);
		clear.setFont(new Font("Sans-serif", Font.BOLD, 16));

		
		new_user = new JButton("New User ?");
		add(new_user);
		new_user.setBounds(480,395,430,30);
		new_user.setBackground(Color.BLACK);
		new_user.setForeground(Color.WHITE);
		new_user.addActionListener(this);
		new_user.setFont(new Font("Sans-serif", Font.BOLD, 16));


	}

	public static void main(String[] args) {
		new Old_user();
	}

	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == clear) {
			new Old_user();
			dispose();
		}
		if( e.getSource() == new_user) {
			new registration();
			dispose();
		}
		
		if( e.getSource()== submit) {
     
			String number_email = num_text.getText().trim();
			char[] pass = pwd_text.getPassword();
			String password = new String(pass);
			String captcha_check = cap_text.getText().trim();
        	
        	 if( !helper.correct_num_email(number_email) ) {
                JOptionPane.showMessageDialog(null, "Enter Correct number OR email!");
        	}
        	
        	else if( !helper.correct_pass(password)) {
                JOptionPane.showMessageDialog(null, "Wrong Password! ");
        	}
        	
        	else if( ! captcha_check.equals(captcha)) {
                JOptionPane.showMessageDialog(null, "Captcha did not match!");
                dispose();
                new Old_user();
        	}
        	else {
        		Conn obj = new Conn();
                String query2 = "select number, email, password , user_id from user Where number ='"+number_email+"' or email = '"+number_email+"';";
                
        		ResultSet rs;
				try {
					rs = obj.s.executeQuery(query2);
				
                
        		boolean stringFound = false;
        		String act_pass="";
        		while (rs.next()) {
                    String number = rs.getString("number");
                    String email = rs.getString("email");
                     act_pass = rs.getString("password");
                     userId = rs.getString("user_id");
                    if (number_email.equals(email) || number_email.equals(number)) {
                        stringFound = true;
                        break;
                    }
                }
                if (stringFound) {
                	if(act_pass.equals(password)) {
                		dispose();
                				new Login(userId);
                				dispose();
                	}else {
                        JOptionPane.showMessageDialog(null, "Incorrect Password!");

                	}
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid number or email address!");

                }
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
            } 
                
                
                
                
                
        	}
	}

}

